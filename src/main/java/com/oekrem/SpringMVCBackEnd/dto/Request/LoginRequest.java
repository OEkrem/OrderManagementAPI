package com.oekrem.SpringMVCBackEnd.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(name = "Login Request Model")
public record LoginRequest (

        @NotBlank(message = "Email is required")
        @Schema(name = "email", example = "firstname@example.com")
        String email,
        @NotBlank(message = "Password is required")
        @Schema(name = "password", example = "1234")
        String password

){

}
