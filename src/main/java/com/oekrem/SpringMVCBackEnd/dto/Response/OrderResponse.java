package com.oekrem.SpringMVCBackEnd.dto.Response;

import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record OrderResponse (
        Long id,
        Long userId,
        LocalDate date,
        OrderStatus orderStatus
){

}
