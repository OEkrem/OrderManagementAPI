package com.oekrem.SpringMVCBackEnd.repository;

import com.oekrem.SpringMVCBackEnd.models.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    List<Payment> findAll();
    Payment addPayment(Payment payment);
    Payment updatePayment(Payment payment);
    void deletePayment(Long id);
    Optional<Payment> getPaymentById(Long id);
    Optional<Payment> getPaymentByOrderId(Long orderId);
}
