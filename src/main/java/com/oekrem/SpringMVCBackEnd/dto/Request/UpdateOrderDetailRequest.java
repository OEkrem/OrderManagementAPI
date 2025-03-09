package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOrderDetailRequest {

    private Long id;
    private Long productId;
    private QuantityType quantityType;
    private BigDecimal quantity;
    private Double price;
    //private Long orderId; // bu bilgi pathvariable olarak alınacak

}
