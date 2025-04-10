package com.oekrem.SpringMVCBackEnd.services.bank;

import com.oekrem.SpringMVCBackEnd.dto.Request.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class GarantiBankasiPaymentService implements BankPaymentService {

    @Override
    public boolean processPayment(PaymentRequest request) {
        System.out.println("Garanti bankasi payment");
        return true;
    }
}
