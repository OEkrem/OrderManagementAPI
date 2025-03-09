package com.oekrem.SpringMVCBackEnd.dto.Request;

import lombok.Builder;

import java.util.List;

@Builder
public record PatchOrderRequest (
        Long userId,
        List<CreateOrderDetailRequest>orderDetailResponses,
        CreatePaymentRequest payment,
        Double total
){
}
