package com.oekrem.SpringMVCBackEnd.services.event;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    private Long orderId;
    private Long customerId;
}