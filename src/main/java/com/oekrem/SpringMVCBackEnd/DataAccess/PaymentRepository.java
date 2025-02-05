package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Payment;

import java.util.List;

public interface PaymentRepository {
    List<Payment> findAll();
    void addPayment(Payment payment);
    void updatePayment(Payment payment);
    void deletePayment(Long id);
    Payment getPaymentById(Long id);
}
