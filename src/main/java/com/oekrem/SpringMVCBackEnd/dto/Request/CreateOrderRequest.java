package com.oekrem.SpringMVCBackEnd.dto.Request;

import lombok.Builder;

@Builder
public record CreateOrderRequest (
        Long userId
){
    // mapper, test sınıfları değiştirilecek
}
