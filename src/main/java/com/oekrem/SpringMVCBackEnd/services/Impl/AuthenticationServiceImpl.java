package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.RegisterRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.RegisterResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions.EMailTakenException;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.services.AuthenticationService;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secretKey;

    private Long jwtExpiryMs = 86400000L;

    @Override
    public UserDetails authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        return userDetailsService.loadUserByUsername(email);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiryMs))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public UserDetails validateToken(String token) {
        String username = extractUsername(token);
        return userDetailsService.loadUserByUsername(username);
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        userService.validateUserEmail(registerRequest.getEmail()).ifPresent(
                p -> {throw new EMailTakenException("E-mail already exists");
                }
        );
        // Yeni kullanıcı oluştur
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // Varsayılan olarak USER rolü ekle
        //user.setRoles(Collections.singleton(new Role("ROLE_USER")));

        UserResponse savedUser = userService.addUser(
                CreateUserRequest.builder()
                        .email(registerRequest.getEmail())
                        .firstName(null)
                        .lastName(null)
                        .username(registerRequest.getUsername())
                        .password(passwordEncoder.encode(registerRequest.getPassword()))
                        .phone(null)
                        .build()
        );
        RegisterResponse registerResponse = RegisterResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .phone(savedUser.getPhone())
                .password(savedUser.getPassword())
                .success(true)
                .message("Success")
                .build();
        return registerResponse;
    }

    private String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    private Key getSigninKey(){
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
