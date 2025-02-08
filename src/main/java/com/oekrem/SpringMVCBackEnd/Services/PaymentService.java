package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.Models.Payment;

import java.util.List;

public interface PaymentService {
    List<PaymentResponse> findAll();
    CreatePaymentRequest addPayment(Long orderId, CreatePaymentRequest createPaymentRequest);
    UpdatePaymentRequest updatePayment(Long orderId, UpdatePaymentRequest updatePayment);
    void deletePayment(Long id);
    PaymentResponse getPaymentById(Long id);

    Payment validatePayment(Long id);
}
