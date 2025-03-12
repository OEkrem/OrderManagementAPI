package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;
import jakarta.validation.Valid;
import lombok.Builder;

import java.util.List;

@Builder
public record PatchOrderRequest (

        @Valid
        List<CreateOrderDetailRequest> orderDetails,
        @Valid
        CreatePaymentRequest payment,

        OrderStatus status
){
}
