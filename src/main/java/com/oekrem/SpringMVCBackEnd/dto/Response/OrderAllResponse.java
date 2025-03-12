package com.oekrem.SpringMVCBackEnd.dto.Response;

import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderAllResponse {

    private Long id;
    private Long userId;
    private List<OrderDetailResponse> orderDetailResponses;
    private PaymentResponse payment;
    private LocalDate date;
    private OrderStatus orderStatus;
    private Double total;

}
