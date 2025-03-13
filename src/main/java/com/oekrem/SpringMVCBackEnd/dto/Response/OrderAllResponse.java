package com.oekrem.SpringMVCBackEnd.dto.Response;

import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
@Schema(name = "OrderAll Response Model", description = "Show us also payment and order details together")
public record OrderAllResponse (
        @Schema(name = "Order id")
        Long id,
        @Schema(name = "User id")
        Long userId,
        @Schema(name = "List of Order details")
        List<OrderDetailResponse> orderDetailResponses,
        @Schema(name = "Payment information")
        PaymentResponse payment,
        @Schema(name = "Time to create an Order", description = "YYYY-MM-DD (LocalDate)", example = "2023-01-01")
        LocalDate date,
        @Schema(name = "Order status", description = "tells whether the order has been approved by the user or not", example = "APPROVED")
        OrderStatus orderStatus,
        @Schema(name = "Total amount", description = "it is sum of all order details price")
        Double total
){

}
