package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.LoginRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.RegisterRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AuthResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.AccessTokenResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.RegisterResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    AuthResponse login(LoginRequest loginRequest);
    ResponseCookie logout();
    ResponseCookie createRefreshToken(LoginRequest loginRequest);

    RegisterResponse register(RegisterRequest registerRequest);
    AccessTokenResponse createAccessTokenByRefreshToken(String refreshToken);

    UserDetails authenticate(String email, String password);
    String generateToken(UserDetails userDetails, Long expireMs);
    UserDetails validateToken(String token);

    boolean isTokenExpired(String token);
}
