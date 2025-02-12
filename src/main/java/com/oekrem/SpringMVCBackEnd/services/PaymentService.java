package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.models.Payment;

import java.util.List;

public interface PaymentService {
    List<PaymentResponse> findAll();
    CreatePaymentRequest addPayment(Long orderId, CreatePaymentRequest createPaymentRequest);
    UpdatePaymentRequest updatePayment(Long orderId, UpdatePaymentRequest updatePayment);
    void deletePayment(Long id);
    PaymentResponse getPaymentById(Long id);
    PaymentResponse getPaymentByOrderId(Long orderId);

    Payment validatePayment(Long id);
    Payment validatePaymentByOrderId(Long orderId);
}
