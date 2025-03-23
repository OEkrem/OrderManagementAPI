package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateOrderDetailRequest (

        @NotNull(message = "Product id is required")
        @Schema(name = "productId", example = "1")
        Long productId,

        @Schema(name = "quantityType", example = "BOX")
        QuantityType quantityType,
        @Schema(name = "quantity", example = "1")
        Integer quantity

){

}
