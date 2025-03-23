package com.oekrem.SpringMVCBackEnd.dto.Response;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Order Detail Response", description = "it is the response of each product added to card")
public class OrderDetailResponse {

    @Schema(name = "Order detail id")
    private Long id;
    @Schema(name = "Product id")
    private Long productId;
    @Schema(name = "Quantity Type", example = "BOX")
    private QuantityType quantityType;
    @Schema(name = "Quantity amount", example = "1")
    private Integer quantity;
    @Schema(name = "Price", description = "it is equals to product price x quantity")
    private Double price;

}
