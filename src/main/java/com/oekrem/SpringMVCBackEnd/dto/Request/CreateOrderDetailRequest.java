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
public class CreateOrderDetailRequest {

    private Long productId;
    private QuantityType quantityType;
    private BigDecimal quantity;
    private BigDecimal price;
    //private Long orderId; // bu bilgi path variable ile alınacak

}
