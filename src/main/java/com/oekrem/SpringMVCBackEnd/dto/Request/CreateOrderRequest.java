package com.oekrem.SpringMVCBackEnd.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(name = "Create Order Request Model", description = "the model used to create an empty order for user")
public record CreateOrderRequest (

        @NotNull(message = "UserId is required")
        @Schema(name = "userId", example = "1")
        Long userId

){
    // mapper, test sınıfları değiştirilecek
}
