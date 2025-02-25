package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.LoginRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.RegisterRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AuthResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.RefreshTokenResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.RegisterResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    AuthResponse login(LoginRequest loginRequest);
    RegisterResponse register(RegisterRequest registerRequest);
    RefreshTokenResponse refreshToken(String refreshToken);

    UserDetails authenticate(String email, String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);

    String generateRefreshToken(UserDetails userDetails);
    boolean isTokenExpired(String token);
}
