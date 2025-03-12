package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record PatchUserRequest (

        String username,

        @ValidPassword
        String password,

        String firstName,
        String lastName,

        @Email(message = "Please enter a valid email")
        String email,

        @Pattern(regexp = "^\\+?[0-9]{1,4}?[\\s\\-]?\\(?\\d{1,4}?\\)?[\\s\\-]?\\d{1,4}[\\s\\-]?\\d{1,4}[\\s\\-]?\\d{1,4}$",
                message = "Please enter a valid phone")
        String phone
){
}
