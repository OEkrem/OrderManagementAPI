package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchPaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.Payment;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

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
                .build();
        PaymentResponse response = paymentMapper.toResponse(payment);

        assertEquals(response.getId(), payment.getId());
        assertEquals(response.getPaymentStatus(), payment.getPaymentStatus());
        assertEquals(response.getPaymentMethod(), payment.getPaymentMethod());
        assertEquals(response.getOrderId(), payment.getOrder().getId());
        assertEquals(response.getAmount(), payment.getAmount());
        assertEquals(response.getDate(), payment.getDate());
    }

    @Test
    public void shouldMapCreateRequestToPayment(){
        CreatePaymentRequest createPaymentRequest = CreatePaymentRequest.builder()
                .paymentStatus(PaymentStatus.SUCCESSFUL)
                .paymentMethod(PaymentMethod.PAYPAL)
                .build();
        Payment response = paymentMapper.toPaymentFromCreateRequest(createPaymentRequest);

        assertEquals(createPaymentRequest.paymentStatus(), response.getPaymentStatus());
        assertEquals(createPaymentRequest.paymentMethod(), response.getPaymentMethod());
    }

    @Test
    public void shouldMapUpdateRequestToPayment(){
        UpdatePaymentRequest updatePaymentRequest = UpdatePaymentRequest.builder()
                .paymentStatus(PaymentStatus.SUCCESSFUL)
                .paymentMethod(PaymentMethod.PAYPAL)
                .build();
        Payment response = paymentMapper.toPaymentFromUpdateRequest(updatePaymentRequest);

        assertEquals(updatePaymentRequest.paymentStatus(), response.getPaymentStatus());
        assertEquals(updatePaymentRequest.paymentMethod(), response.getPaymentMethod());
    }

    @Test
    public void shouldMapPatchPaymentRequestToPayment(){
        PatchPaymentRequest patchPaymentRequest = PatchPaymentRequest.builder()
                .paymentStatus(PaymentStatus.SUCCESSFUL)
                .paymentMethod(PaymentMethod.PAYPAL)
                .build();
        Payment payment = Payment.builder().build();
        paymentMapper.patchPayment(patchPaymentRequest, payment);

        assertEquals(patchPaymentRequest.paymentStatus(), payment.getPaymentStatus());
        assertEquals(patchPaymentRequest.paymentMethod(), payment.getPaymentMethod());
    }

}
