package com.oekrem.SpringMVCBackEnd.Dto.Mapper.CustomMapper;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.Models.Order;
import com.oekrem.SpringMVCBackEnd.Models.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponse toResponse(Payment payment){
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(payment.getId());
        paymentResponse.setDescription(payment.getDescription());
        paymentResponse.setAmount(payment.getAmount());
        paymentResponse.setPaymentStatus(payment.getPaymentStatus());
        paymentResponse.setPaymentMethod(payment.getPaymentMethod());
        paymentResponse.setDate(payment.getDate());
        paymentResponse.setOrderId(payment.getOrder().getId());
        return paymentResponse;
    }

    public Payment toPaymentFromResponse(PaymentResponse paymentResponse){
        Order order = new Order(); order.setId(paymentResponse.getOrderId());
        Payment payment = new Payment();
        payment.setId(paymentResponse.getId());
        payment.setDescription(paymentResponse.getDescription());
        payment.setAmount(paymentResponse.getAmount());
        payment.setPaymentStatus(paymentResponse.getPaymentStatus());
        payment.setPaymentMethod(paymentResponse.getPaymentMethod());
        payment.setDate(paymentResponse.getDate());
        payment.setOrder(order);
        return payment;
    }

    public CreatePaymentRequest toCreatePaymentRequest(Payment payment){
        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest();
        createPaymentRequest.setDescription(payment.getDescription());
        createPaymentRequest.setAmount(payment.getAmount());
        createPaymentRequest.setPaymentStatus(payment.getPaymentStatus());
        createPaymentRequest.setPaymentMethod(payment.getPaymentMethod());
        createPaymentRequest.setDate(payment.getDate());
        return createPaymentRequest;
    }

    public Payment toPaymentFromCreatePaymentRequest(CreatePaymentRequest createPaymentRequest){
        Payment payment = new Payment();
        payment.setDescription(createPaymentRequest.getDescription());
        payment.setAmount(createPaymentRequest.getAmount());
        payment.setPaymentStatus(createPaymentRequest.getPaymentStatus());
        payment.setPaymentMethod(createPaymentRequest.getPaymentMethod());
        payment.setDate(createPaymentRequest.getDate());
        return payment;
    }

    public UpdatePaymentRequest toUpdatePaymentRequest(Payment payment){
        UpdatePaymentRequest updatePaymentRequest = new UpdatePaymentRequest();
        updatePaymentRequest.setId(payment.getId());
        updatePaymentRequest.setDescription(payment.getDescription());
        updatePaymentRequest.setAmount(payment.getAmount());
        updatePaymentRequest.setPaymentStatus(payment.getPaymentStatus());
        updatePaymentRequest.setPaymentMethod(payment.getPaymentMethod());
        updatePaymentRequest.setDate(payment.getDate());
        return updatePaymentRequest;
    }

    public Payment toPaymentFromUpdatePaymentRequest(UpdatePaymentRequest updatePaymentRequest){
        Payment payment = new Payment();
        payment.setId(updatePaymentRequest.getId());
        payment.setDescription(updatePaymentRequest.getDescription());
        payment.setAmount(updatePaymentRequest.getAmount());
        payment.setPaymentStatus(updatePaymentRequest.getPaymentStatus());
        payment.setPaymentMethod(updatePaymentRequest.getPaymentMethod());
        payment.setDate(updatePaymentRequest.getDate());
        return payment;
    }

}
