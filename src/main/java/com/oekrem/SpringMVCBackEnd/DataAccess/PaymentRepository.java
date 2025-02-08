package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    List<Payment> findAll();
    Payment addPayment(Payment payment);
    Payment updatePayment(Payment payment);
    void deletePayment(Long id);
    Optional<Payment> getPaymentById(Long id);
}
