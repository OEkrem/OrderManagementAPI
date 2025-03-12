package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateOrderDetailRequest (
        @NotNull(message = "Product is required")
        Long productId,

        @NotNull(message = "Quantity Type is required")
        QuantityType quantityType,

        @NotNull(message = "Quantity is required")
        Integer quantity
){

}
