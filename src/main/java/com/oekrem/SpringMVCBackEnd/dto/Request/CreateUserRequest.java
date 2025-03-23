package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.Role;
import com.oekrem.SpringMVCBackEnd.validation.ValidPassword;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.util.Set;

@Builder
@Schema(name = "Create User Request Model")
public record CreateUserRequest (

        @NotBlank(message = "Username is required")
        @Schema(name = "username", example = "Username37")
        String username,

        @NotBlank(message = "Password is required")
        @ValidPassword
        @Schema(name = "password", example = "1234")
        String password,

        @Schema(name = "firstName", example = "Firstname")
        String firstName,
        @Schema(name = "lastName", example = "Lastname")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Please enter a valid email")
        @Schema(name = "email", example = "firstname@examle.com")
        String email,

        @Pattern(regexp = "^\\+?[0-9]{1,4}?[\\s\\-]?\\(?\\d{1,4}?\\)?[\\s\\-]?\\d{1,4}[\\s\\-]?\\d{1,4}[\\s\\-]?\\d{1,4}$",
                message = "Please enter a valid phone")
        @Schema(name = "phone", example = "+12 (123) 123 12 12")
        String phone,

        Set<Role> roles

){

}
