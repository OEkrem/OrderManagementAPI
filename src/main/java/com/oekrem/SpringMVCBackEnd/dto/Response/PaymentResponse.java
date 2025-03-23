package com.oekrem.SpringMVCBackEnd.dto.Response;

import com.oekrem.SpringMVCBackEnd.models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Schema(name = "Payment Response Model", description = "it is a response of payment information")
public record PaymentResponse (
        @Schema(name = "Payment id")
        Long id,
        @Schema(name = "Amount of pay")
        Double amount,
        @Schema(name = "Payment status", example = "PENDING")
        PaymentStatus paymentStatus,
        @Schema(name = "Payment method", example = "CREDIT_CARD")
        PaymentMethod paymentMethod,
        @Schema(name = "Date of created payment")
        LocalDateTime date,
        @Schema(name = "Order id")
        Long orderId
){

}
