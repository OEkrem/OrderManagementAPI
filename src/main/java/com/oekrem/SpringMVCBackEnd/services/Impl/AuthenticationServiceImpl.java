package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateUserRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.LoginRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.RegisterRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AuthResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.RefreshTokenResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.RegisterResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.SecurityExceptions.TokenAlreadyExpiredException;
import com.oekrem.SpringMVCBackEnd.exceptions.SecurityExceptions.UserRegistrationException;
import com.oekrem.SpringMVCBackEnd.exceptions.UserExceptions.EMailTakenException;
import com.oekrem.SpringMVCBackEnd.models.RefreshToken;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.security.OrderUserDetails;
import com.oekrem.SpringMVCBackEnd.services.AuthenticationService;
import com.oekrem.SpringMVCBackEnd.services.RefreshTokenService;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
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
    private final RefreshTokenService refreshTokenService;

    @Value("${jwt.secret}")
    private String secretKey;

    private Long jwtExpiryMs = 900000L; // 15dk
    private Long jwtExpireMsRefreshToken =  2*86400000L; // 2 gün

    @Override
    @Transactional
    public AuthResponse login(LoginRequest loginRequest) {

        UserDetails userDetails = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = generateToken(userDetails);

        // Kullanıcı doğrulaması - çünkü diğer türlü var olan tokeni silemiyoruz
        User user = userService.getUserByEmail(loginRequest.getEmail());

        // kullanıcı refresh tokenleri silindi
        refreshTokenService.deleteRefreshTokensByUserId(user.getId());
        String refreshToken = generateRefreshToken(userDetails);
        // oluşturulan yeni refresh token databaseye kaydedildi
        refreshTokenService.createRefreshToken(user, refreshToken);

        return AuthResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .expiresIn(30000L)  // Config üzerinden alınabilir
                .build();
    }


    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest registerRequest) {

        /*
            1- Kullanıcının email adresi daha önce kullanılmış mı valid edilir
            2- İlgili mail yok ise kullanıcı kayıt edilir (şifresi encode edilerek)
            3- İlgili user üzerinden userdetails oluşturulur (orderdetails)
            4- User için generetatoken ile accesstoken oluşturulur
            5- Ve kullanıcıya bu bilgiler döndürülür
         */

        // E mail zaten var mı kontrolü yaıldı.
        userService.validateUserEmail(registerRequest.getEmail()).ifPresent(
                p -> {throw new EMailTakenException("E-mail already exists");
                }
        );

        //  Kullanıcı veri tabanina kaydedildi..
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
        if(savedUser == null) { throw new UserRegistrationException("User registration failed");}

        // ilk oturum için accesstoken oluşturuluyor
        UserDetails savedUserDetails = new OrderUserDetails(User.builder()
                .id(savedUser.getId()).email(savedUser.getEmail()).password(savedUser.getPassword()).username(savedUser.getUsername())
                .firstName(null).lastName(null).phone(null).addresses(null).build());
        String accessToken = generateToken(savedUserDetails);

        // Access Token oluşturuldu ve response...
        return RegisterResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .phone(savedUser.getPhone())
                .password(savedUser.getPassword())
                .accessToken(accessToken)
                .success(true)
                .message("Success")
                .build();
    }

    @Override
    @Transactional
    public RefreshTokenResponse refreshToken(String refreshToken) {

        /*
            1- Frontta saklı refresh token gelicek
            2- Bu refresh tokenin süresi geçmiş mi öğrenilecek
            3- Kayıtlı is ilgili user çağırılacak ve userdetails de öyle
            4- Yeni bir accesstoken üretilecek
            5- Üretilen yeni accesstoken frontta geri iletilecek
         */


        //System.out.println("Refresh Token: " + refreshToken);
        // gelen refreshToken isteğinin formatı kontrol ediliyor. Başındaki ve sonundaki "" ifadeleri kaldırıldı.. Yoksa extract metodunda hata veriyordu.
        String newRefreshToken = null;
        if (refreshToken.startsWith("\"") && refreshToken.endsWith("\""))
            newRefreshToken = refreshToken.substring(1, refreshToken.length() - 1);
        if(newRefreshToken == null)
            newRefreshToken = refreshToken;


        // Token geçerlilik süresi kontrol ediliyor.
        if(isTokenExpired(newRefreshToken))
            throw new TokenAlreadyExpiredException("Refresh token expired: " + refreshToken);
        //System.out.println("Existed token expire olmuş mu kontrol edildi");


        // token not found doğrulaması yapıldı
        //RefreshToken existedToken = refreshTokenService.getRefreshTokenByToken(refreshToken); --> veritabanından böyle çekmek hataya sebep oldu
        String email = extractUsername(newRefreshToken);
        RefreshToken existedToken = refreshTokenService.getRefreshTokenByUserEmail(email);
        //System.out.println("Existed token çekildi: " + existedToken.getRefreshToken() + "/ Userid: " + existedToken.getUser().getId());

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String accessToken = generateToken(userDetails);
        System.out.println("Access token oluşturuldu ve response...: " + accessToken);
        return RefreshTokenResponse.builder()
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
    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpireMsRefreshToken))
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
