package com.oekrem.SpringMVCBackEnd.dto.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
