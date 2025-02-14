package com.oekrem.SpringMVCBackEnd.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {

    private String username;
    private String password;

    private String firstName;
    private String lastName;

    private String email;
    private String phone;

}
