package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Models.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();
    void addPayment(Payment payment);
    void updatePayment(Payment payment);
    void deletePayment(Long id);
    Payment getPaymentById(Long id);
}
