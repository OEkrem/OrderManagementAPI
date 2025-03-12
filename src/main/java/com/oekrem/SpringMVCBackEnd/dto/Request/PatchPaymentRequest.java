package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import lombok.Builder;

@Builder
public record PatchPaymentRequest(

        Double amount,
        PaymentStatus paymentStatus,
        PaymentMethod paymentMethod
) {
}
