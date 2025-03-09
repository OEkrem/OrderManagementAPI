package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PatchPaymentRequest(
        Long id,
        String description,
        Double amount,
        PaymentStatus paymentStatus,
        PaymentMethod paymentMethod,
        LocalDateTime date
) {
}
