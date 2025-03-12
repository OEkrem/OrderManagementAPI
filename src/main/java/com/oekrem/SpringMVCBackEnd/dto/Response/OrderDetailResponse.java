package com.oekrem.SpringMVCBackEnd.dto.Response;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {

    private Long id;
    private Long productId;
    private QuantityType quantityType;
    private Integer quantity;
    private Double price;

}
