package com.oekrem.SpringMVCBackEnd.dto.Request;

import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(name = "Update Order Request Model")
public record UpdateOrderRequest (

        @NotNull(message = "Order id is required")
        @Schema(name = "id", example = "1")
        Long id,

        @Valid
        @Schema(name = "orderDetails")
        List<CreateOrderDetailRequest> orderDetails,
        @Valid
        @Schema(name = "payment")
        CreatePaymentRequest payment,

        @Schema(name = "status")
        OrderStatus status

        /*@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate date,
        Double total*/
){
}