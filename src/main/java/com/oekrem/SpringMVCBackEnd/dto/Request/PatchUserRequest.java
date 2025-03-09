package com.oekrem.SpringMVCBackEnd.dto.Request;

import lombok.Builder;

@Builder
public record PatchUserRequest (
        String username,
        String password,

        String firstName,
        String lastName,

        String email,
        String phone
){
}
