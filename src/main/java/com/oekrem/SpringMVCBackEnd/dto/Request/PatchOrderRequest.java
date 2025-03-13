package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(name = "Patch Order Request Model")
public record PatchOrderRequest (

        @Valid
        @Schema(name = "orderDetails")
        List<CreateOrderDetailRequest> orderDetails,
        @Valid
        @Schema(name = "payment")
        CreatePaymentRequest payment,

        @Schema(name = "status", example = "PENDING")
        OrderStatus status

){
}
