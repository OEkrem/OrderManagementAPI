package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.LoginRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.RegisterRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AuthResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.RegisterResponse;
import com.oekrem.SpringMVCBackEnd.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = authenticationService.generateToken(userDetails);
        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .expiresIn(86400000L)
                .build();
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
}
