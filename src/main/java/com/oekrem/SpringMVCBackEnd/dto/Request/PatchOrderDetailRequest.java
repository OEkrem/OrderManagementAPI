package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import lombok.Builder;

@Builder
public record PatchOrderDetailRequest(
        Long productId,
        QuantityType quantityType,
        Integer quantity,
        Double price
) {
}
