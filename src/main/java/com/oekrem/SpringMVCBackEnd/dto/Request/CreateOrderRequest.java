package com.oekrem.SpringMVCBackEnd.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {

    /*private List<CreateOrderDetailRequest> orderDetailList;
    private CreatePaymentRequest payment;*/
    private LocalDate date;
    private Double total;

}
