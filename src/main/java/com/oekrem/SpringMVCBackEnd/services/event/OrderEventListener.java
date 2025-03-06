package com.oekrem.SpringMVCBackEnd.services.event;

import com.oekrem.SpringMVCBackEnd.dto.Response.UserResponse;
import com.oekrem.SpringMVCBackEnd.email.EMailRequest;
import com.oekrem.SpringMVCBackEnd.email.EMailService;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventListener {

    private final EMailService emailService;
    private final UserService userService;

    @RabbitListener(queues = "orderQueue")
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        UserResponse user = userService.getUserById(event.getCustomerId());
        String siparisMesaji = "Sayın " + user.getEmail() + "\nSiparişiniz alınmıştır.\nBizi tercih ettiğiniz için teşşekürler :)";
        emailService.sendSimpleEmail(EMailRequest.builder()
                .to(user.getEmail())
                .subject("Sipariş Oluşturuldu")
                .content(siparisMesaji)
                .build());
    }

}
