package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.LoginRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.RegisterRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.AddressResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.AuthResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.AccessTokenResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.RegisterResponse;
import com.oekrem.SpringMVCBackEnd.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller", description = "Manages user authentication operations")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Authenticate user and return access token",
                description = "Validates user credentials and returns an access token for authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing input data)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, authentication required (JWT token)"),
            @ApiResponse(responseCode = "404", description = "User Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, authenticationService.createRefreshToken(loginRequest))
                .body(authenticationService.login(loginRequest));
    }

    @Operation(summary = "Register a new user account",
                description = "Create a new user with provided credentials and returns a confirmation response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RegisterResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request, (Invalid or missing input data)"),
            @ApiResponse(responseCode = "409", description = "Conflict, User already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(request));
    }

    @Operation(summary = "Refresh authentication token",
                description = "Exchanges a valid refresh token for a new access token to maintain user authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccessTokenResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request, Invalid refresh token or missing token"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Expired or invalid refresh token"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/refresh")
    public ResponseEntity<AccessTokenResponse> refreshToken(@CookieValue(value = "refreshToken", required = false) String refreshToken) {
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(AccessTokenResponse.builder().message("Refresh Token is not found!").build());
        }
        return ResponseEntity.ok(authenticationService.createAccessTokenByRefreshToken(refreshToken));
    }
}
