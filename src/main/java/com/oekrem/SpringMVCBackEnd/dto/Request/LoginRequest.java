package com.oekrem.SpringMVCBackEnd.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequest (
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        String password
){

}
