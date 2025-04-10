package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchPaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.Payment;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import org.springframework.data.domain.Page;

public interface PaymentService {
    Page<PaymentResponse> findAll(int page, int size, PaymentStatus status);
    PaymentResponse updatePayment(Long paymentId, UpdatePaymentRequest updatePayment);
    PaymentResponse patchPayment(Long paymentId, PatchPaymentRequest patchPayment);
    void deletePayment(Long paymentId);
    PaymentResponse getPaymentById(Long paymentId);

    Payment validatePayment(Long paymentId);


    // For OrderService
    PaymentResponse addPayment(Order order, CreatePaymentRequest createPaymentRequest);

    boolean processPayment(PaymentRequest request);
}
