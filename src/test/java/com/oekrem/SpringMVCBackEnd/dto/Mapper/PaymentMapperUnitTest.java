package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.Payment;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentMapperUnitTest {

    private PaymentMapper paymentMapper;

    @BeforeEach
    public void setUp() {
        paymentMapper = Mappers.getMapper(PaymentMapper.class);
    }

    @Test
    public void shouldMapPaymentToResponse(){
        Payment payment = Payment.builder()
                .id(1L)
                .paymentStatus(PaymentStatus.SUCCESSFUL)
                .paymentMethod(PaymentMethod.PAYPAL)
                .order(Order.builder().id(1L).build())
                .amount(200D)
                .date(LocalDateTime.now())
                .description("test")
                .build();
        PaymentResponse response = paymentMapper.toResponse(payment);

        assertEquals(response.getId(), payment.getId());
        assertEquals(response.getPaymentStatus(), payment.getPaymentStatus());
        assertEquals(response.getPaymentMethod(), payment.getPaymentMethod());
        assertEquals(response.getOrderId(), payment.getOrder().getId());
        assertEquals(response.getAmount(), payment.getAmount());
        assertEquals(response.getDate(), payment.getDate());
        assertEquals(response.getDescription(), payment.getDescription());
    }

    @Test
    public void shouldMapCreateRequestToPayment(){
        CreatePaymentRequest createPaymentRequest = CreatePaymentRequest.builder()
                .paymentStatus(PaymentStatus.SUCCESSFUL)
                .paymentMethod(PaymentMethod.PAYPAL)
                .amount(200D)
                .date(LocalDateTime.now())
                .description("test")
                .build();
        Payment response = paymentMapper.toPaymentFromCreateRequest(createPaymentRequest);

        assertEquals(createPaymentRequest.getPaymentStatus(), response.getPaymentStatus());
        assertEquals(createPaymentRequest.getPaymentMethod(), response.getPaymentMethod());
        assertEquals(createPaymentRequest.getAmount(), response.getAmount());
        assertEquals(createPaymentRequest.getDate(), response.getDate());
        assertEquals(createPaymentRequest.getDescription(), response.getDescription());
    }

    @Test
    public void shouldMapUpdateRequestToPayment(){
        UpdatePaymentRequest updatePaymentRequest = UpdatePaymentRequest.builder()
                .id(1L)
                .paymentStatus(PaymentStatus.SUCCESSFUL)
                .paymentMethod(PaymentMethod.PAYPAL)
                .amount(200D)
                .date(LocalDateTime.now())
                .description("test")
                .build();
        Payment response = paymentMapper.toPaymentFromUpdateRequest(updatePaymentRequest);

        assertEquals(updatePaymentRequest.getId(), response.getId());
        assertEquals(updatePaymentRequest.getPaymentStatus(), response.getPaymentStatus());
        assertEquals(updatePaymentRequest.getPaymentMethod(), response.getPaymentMethod());
        assertEquals(updatePaymentRequest.getAmount(), response.getAmount());
        assertEquals(updatePaymentRequest.getDate(), response.getDate());
        assertEquals(updatePaymentRequest.getDescription(), response.getDescription());
    }

}
