package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(name = "Create Order Detail Request", description = "every product added to the cart will be created with this model")
public record CreateOrderDetailRequest (

        @NotNull(message = "Product is required")
        @Schema(name = "productId", example = "1")
        Long productId,

        @NotNull(message = "Quantity Type is required")
        @Schema(name = "quantityType", example = "PIECE")
        QuantityType quantityType,

        @NotNull(message = "Quantity is required")
        @Schema(name = "quantity", example = "1")
        Integer quantity
){

}
