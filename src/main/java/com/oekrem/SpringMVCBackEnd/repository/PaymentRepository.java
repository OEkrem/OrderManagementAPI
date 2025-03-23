package com.oekrem.SpringMVCBackEnd.repository;

import com.oekrem.SpringMVCBackEnd.models.Payment;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PaymentRepository {
    Page<Payment> findAll(Pageable pageable);
    Page<Payment> findByPaymentStatus(Pageable pageable, PaymentStatus paymentStatus);
    Payment addPayment(Payment payment);
    Payment updatePayment(Payment payment);
    void deletePayment(Long id);
    Optional<Payment> getPaymentById(Long id);
    Optional<Payment> getPaymentByOrderId(Long orderId);


    Optional<User> getOwnerById(Long id);
}
