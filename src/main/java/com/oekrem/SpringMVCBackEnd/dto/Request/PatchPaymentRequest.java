package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Patch Payment Request Model")
public record PatchPaymentRequest(

        @Schema(name = "amount", example = "299.0")
        Double amount,
        // amount kaldırılabilir gibi belki ççünkü otomatik gerçekleşsin istiyorum

        @Schema(name = "paymentStatus", example = "SUCCESSFUL")
        PaymentStatus paymentStatus,
        @Schema(name = "paymentMethod", example = "CREDIT_CARD")
        PaymentMethod paymentMethod
) {
}
