package com.oekrem.SpringMVCBackEnd.dto.Response;

import lombok.Builder;

@Builder
public record AccessTokenResponse(
        String token,
        String message,
        boolean success
) {
}
