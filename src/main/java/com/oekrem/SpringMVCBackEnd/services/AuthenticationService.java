package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.RegisterRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.RegisterResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    UserDetails authenticate(String email, String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);

    RegisterResponse register(RegisterRequest registerRequest);
}
