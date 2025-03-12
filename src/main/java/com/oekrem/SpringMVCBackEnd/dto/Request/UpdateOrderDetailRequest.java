package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOrderDetailRequest {

    @NotNull(message = "Product id is required")
    private Long productId;

    @Builder.Default
    private QuantityType quantityType = QuantityType.UNKNOWN;
    @Builder.Default
    private Integer quantity = 0;

}
