package com.oekrem.SpringMVCBackEnd.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterRequest (

        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        String password
){

}
