package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Patch Order Detail Request Model")
public record PatchOrderDetailRequest(

        @Schema(name = "productId", example = "1")
        Long productId,
        @Schema(name = "quantityType", example = "PIECE")
        QuantityType quantityType,
        @Schema(name = "quantity", example = "1")
        Integer quantity,

        @Schema(name = "price", example = "299.0")
        Double price
        // belki price değerleri otomatikleştirilebilir yani

) {
}
