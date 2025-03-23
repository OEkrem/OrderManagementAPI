package com.oekrem.SpringMVCBackEnd.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Access Token Response Model", description = "its using for get access token with refresh token")
public record AccessTokenResponse(

        @Schema(description = "Access Token")
        String token,
        @Schema(description = "Message")
        String message,
        @Schema(description = "Success status", example = "true")
        boolean success
) {
}
