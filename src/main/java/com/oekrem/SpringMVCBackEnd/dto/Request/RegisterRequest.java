package com.oekrem.SpringMVCBackEnd.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(name = "Register Request Model")
public record RegisterRequest (

        @NotBlank(message = "Username is required")
        @Schema(name = "username", example = "Username")
        String username,
        @NotBlank(message = "Email is required")
        @Schema(name = "email", example = "firstname@example.com")
        String email,
        @NotBlank(message = "Password is required")
        @Schema(name = "password", example = "1234")
        String password

){
}
