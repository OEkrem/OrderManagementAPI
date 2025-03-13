package com.oekrem.SpringMVCBackEnd.dto.Response;

import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(name = "Order Response Model", description = "it doesnt show payment and orderdetails, unlike 'OrderAllRespons' Model")
public record OrderResponse (
        @Schema(name = "Order id")
        Long id,
        @Schema(name = "User id")
        Long userId,
        @Schema(name = "Date of created order")
        LocalDate date,
        @Schema(name = "Order status")
        OrderStatus orderStatus
){

}
