package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record UpdateOrderRequest (
        @NotNull(message = "Order id is required")
        Long id,

        @Valid
        List<CreateOrderDetailRequest> orderDetails,
        @Valid
        CreatePaymentRequest payment,

        OrderStatus status

        /*@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date,
        Double total*/
){
}