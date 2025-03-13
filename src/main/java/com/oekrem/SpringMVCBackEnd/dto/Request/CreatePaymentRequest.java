package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(name = "Create Payment Request Model")
public record CreatePaymentRequest (

        @NotNull(message = "Order id is required")
        @Schema(name = "orderId", example = "1")
        Long orderId,

        @NotNull(message = "Payment Status is required")
        @Schema(name = "paymentStatus", example = "PENDING")
        PaymentStatus paymentStatus,

        // belki status'e gerek olmayabilir knk

        @NotNull(message = "Payment Method is required")
        @Schema(name = "paymentMethod", example = "BANK_TRANSFER")
        PaymentMethod paymentMethod
){

}
