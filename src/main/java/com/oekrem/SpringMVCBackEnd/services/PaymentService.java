package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchPaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.models.Payment;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import org.springframework.data.domain.Page;

public interface PaymentService {
    Page<PaymentResponse> findAll(int page, int size, PaymentStatus status);
    PaymentResponse addPayment(Long orderId, CreatePaymentRequest createPaymentRequest);
    PaymentResponse updatePayment(Long orderId, UpdatePaymentRequest updatePayment);
    PaymentResponse patchPayment(Long orderId, PatchPaymentRequest patchPayment);
    void deletePayment(Long id);
    PaymentResponse getPaymentById(Long id);
    PaymentResponse getPaymentByOrderId(Long orderId);

    Payment validatePayment(Long id);
    Payment validatePaymentByOrderId(Long orderId);
}
