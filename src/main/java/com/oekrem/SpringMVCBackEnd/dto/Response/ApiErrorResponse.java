package com.oekrem.SpringMVCBackEnd.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Api Error Response")
public record ApiErrorResponse (
        @Schema(name = "Error Message")
        String message,
        @Schema(name = "Error status code")
        int status
){

}
