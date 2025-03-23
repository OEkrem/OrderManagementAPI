package com.oekrem.SpringMVCBackEnd.dto.Response;

import com.oekrem.SpringMVCBackEnd.models.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Set;

@Builder
@Schema(name = "Register Response Model")
public record RegisterResponse (
        @Schema(name = "User id")
        Long id,
        @Schema(name = "User name")
        String username,
        @Schema(name = "Password")
        String password,

        @Schema(name = "Firstname")
        String firstName,
        @Schema(name = "Lastname")
        String lastName,
        @Schema(name = "Email")
        String email,
        @Schema(name = "Phone")
        String phone,

        @Schema(name = "Status of register")
        boolean success,
        @Schema(name = "Access token")
        String accessToken,
        @Schema(name = "Message")
        String message,

        @Schema(name = "Roles")
        Set<Role> roles
){

}
