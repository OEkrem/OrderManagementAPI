package com.oekrem.SpringMVCBackEnd.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private Long id;
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private boolean success;
    private String message;
}
