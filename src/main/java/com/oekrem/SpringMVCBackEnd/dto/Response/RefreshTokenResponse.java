package com.oekrem.SpringMVCBackEnd.dto.Response;

import lombok.Builder;

@Builder
public record RefreshTokenResponse(
        String token,
        String message,
        boolean success
) {
}
