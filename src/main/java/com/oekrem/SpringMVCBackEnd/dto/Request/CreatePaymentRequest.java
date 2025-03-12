package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreatePaymentRequest (

        @NotNull(message = "Order id is required")
        Long orderId,

        @NotNull(message = "Payment Status is required")
        PaymentStatus paymentStatus,

        @NotNull(message = "Payment Method is required")
        PaymentMethod paymentMethod
){

}
