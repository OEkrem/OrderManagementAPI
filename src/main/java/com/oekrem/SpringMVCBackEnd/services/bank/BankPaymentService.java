package com.oekrem.SpringMVCBackEnd.services.bank;

import com.oekrem.SpringMVCBackEnd.dto.Request.PaymentRequest;

public interface BankPaymentService {
    boolean processPayment(PaymentRequest request);
}
