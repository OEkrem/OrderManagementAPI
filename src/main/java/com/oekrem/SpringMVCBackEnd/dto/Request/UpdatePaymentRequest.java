package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
@Schema(name = "Update Payment Request Model")
public record UpdatePaymentRequest (

        @Schema(name = "amount", example = "299.0")
        Double amount,
        // amount bilgisi belki kaldırılabilir, ancak ilerde promosyon kodu ile indirim gelirse diye saklıyorum ama tabi yine gerek olmayabilir

        @Schema(name = "paymentStatus", example = "PENDING")
        PaymentStatus paymentStatus,
        @Schema(name = "paymentMethod", example = "PAYPAL")
        PaymentMethod paymentMethod

){
}
