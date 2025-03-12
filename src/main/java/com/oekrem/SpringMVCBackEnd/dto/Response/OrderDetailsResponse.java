package com.oekrem.SpringMVCBackEnd.dto.Response;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderDetailsResponse (

        List<OrderDetailResponse> orderDetails
){

}
