package com.oekrem.SpringMVCBackEnd.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Schema(name = "User Response Model")
public record UserResponse (
        @Schema(name = "User id")
        Long id,
        @Schema(name = "Username")
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
        String phone
){

}
