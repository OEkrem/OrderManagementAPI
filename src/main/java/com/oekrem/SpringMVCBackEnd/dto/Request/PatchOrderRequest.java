package com.oekrem.SpringMVCBackEnd.dto.Request;

import lombok.Builder;

import java.util.List;

@Builder
public record PatchOrderRequest (
        Long id,
        List<CreateOrderDetailRequest> orderDetails,
        CreatePaymentRequest payment,
        Double total
){
}
