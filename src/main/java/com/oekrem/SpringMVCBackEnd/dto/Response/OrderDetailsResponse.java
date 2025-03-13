package com.oekrem.SpringMVCBackEnd.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(name = "Order Details Response Model", description = "it is a response of all order details in a list")
public record OrderDetailsResponse (
        @Schema(name = "Order details list")
        List<OrderDetailResponse> orderDetails
){

}
