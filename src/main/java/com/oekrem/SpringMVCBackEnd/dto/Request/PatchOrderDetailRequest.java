package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PatchOrderDetailRequest(
        Long id,
        Long productId,
        QuantityType quantityType,
        BigDecimal quantity,
        Double price
) {
}
