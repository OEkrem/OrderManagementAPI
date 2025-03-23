package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.LoginRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.RegisterRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AuthResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.AccessTokenResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.RegisterResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.SecurityExceptions.TokenAlreadyExpiredException;
import com.oekrem.SpringMVCBackEnd.exceptions.SecurityExceptions.UserRegistrationException;
import com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions.EMailTakenException;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.security.OrderUserDetails;
import com.oekrem.SpringMVCBackEnd.services.AuthenticationService;
import com.oekrem.SpringMVCBackEnd.services.RefreshTokenService;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    @Value("${jwt.secret}")
    private String secretKey;

    private final Long jwtExpiryMs = 900000L; // 15dk 900000
    private final Long jwtExpireMsRefreshToken =  2*86400000L; // 2 gün

    @Override
    @Transactional
    public AuthResponse login(LoginRequest loginRequest) {
        UserDetails userDetails = authenticate(loginRequest.email(), loginRequest.password());
        String token = generateToken(userDetails, jwtExpiryMs);

        return AuthResponse.builder()
                .token(token)
                .expiresIn(jwtExpiryMs)
                .build();
    }

    @Override
    public ResponseCookie logout(){
        return ResponseCookie.from("refreshToken", "")
                .path("/")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .maxAge(0)
                .build();
    }

    @Override
    public ResponseCookie createRefreshToken(LoginRequest loginRequest) {
        UserDetails userDetails = authenticate(loginRequest.email(), loginRequest.password());
        User user = userService.getUserByEmail(loginRequest.email());

        refreshTokenService.deleteRefreshTokensByUserId(user.getId());
        String refreshToken = generateToken(userDetails, jwtExpireMsRefreshToken);
        refreshTokenService.createRefreshToken(user, refreshToken);

        return ResponseCookie.from("refreshToken", refreshToken)
                .path("/")
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .maxAge(Duration.ofMillis(jwtExpireMsRefreshToken))
                .build();
    }


    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest registerRequest) {

        userService.validateUserEmail(registerRequest.email()).ifPresent(
                p -> {throw new EMailTakenException("E-mail already exists");
                }
        );

        // ROLE_USER ekleme işlemi için addUser()
        UserResponse savedUser = userService.addUser(
                CreateUserRequest.builder()
                        .email(registerRequest.email())
                        .username(registerRequest.username())
                        .password(passwordEncoder.encode(registerRequest.password()))
                        .build()
        );
        if(savedUser == null) { throw new UserRegistrationException("User registration failed");}

        UserDetails savedUserDetails = new OrderUserDetails(User.builder()
                .id(savedUser.id()).email(savedUser.email()).password(savedUser.password()).username(savedUser.username())
                .roles(savedUser.roles())
                .build());
        String accessToken = generateToken(savedUserDetails, jwtExpiryMs);

        return RegisterResponse.builder()
                .id(savedUser.id())
                .username(savedUser.username())
                .email(savedUser.email())
                .firstName(savedUser.firstName())
                .lastName(savedUser.lastName())
                .phone(savedUser.phone())
                .password(savedUser.password())
                .accessToken(accessToken)
                .roles(savedUser.roles())
                .success(true)
                .message("Success")
                .build();
    }

    @Override
    @Transactional
    public AccessTokenResponse createAccessTokenByRefreshToken(String refreshToken) {

        String newRefreshToken = null;
        if (refreshToken.startsWith("\"") && refreshToken.endsWith("\""))
            newRefreshToken = refreshToken.substring(1, refreshToken.length() - 1);
        if(newRefreshToken == null)
            newRefreshToken = refreshToken;


        if(isTokenExpired(newRefreshToken)){
            throw new TokenAlreadyExpiredException("Refresh token expired: " + refreshToken);
        }

        //RefreshToken existedToken = refreshTokenService.getRefreshTokenByToken(refreshToken); --> veritabanından böyle çekmek hataya sebep oldu
        String email = extractUsername(newRefreshToken);

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String accessToken = generateToken(userDetails, jwtExpiryMs);
        System.out.println("Access token oluşturuldu ve response...: " + accessToken);
        return AccessTokenResponse.builder()
                .token(accessToken)
                .success(true)
                .message("Access token created successfully")
                .build();
    }

    @Override
    public UserDetails authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        return userDetailsService.loadUserByUsername(email);
    }

    @Override
    public String generateToken(UserDetails userDetails, Long expireTime) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenExpired(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSigninKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        return claims.getExpiration().before(new Date());
    }


    @Override
    public UserDetails validateToken(String token) {
        String username = extractUsername(token);
        return userDetailsService.loadUserByUsername(username);
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
