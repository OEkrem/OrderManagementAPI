package com.oekrem.SpringMVCBackEnd.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Authentication Response")
public record AuthResponse (
        @Schema(name = "Access Token")
        String token,
        @Schema(name = "Expire Date")
        Long expiresIn
) {

}
