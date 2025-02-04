package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Payment;
import com.oekrem.SpringMVCBackEnd.Models.User;

import java.util.List;

public interface PaymentRepository {
    List<Payment> findAll();
    void addPayment(Payment payment);
    void updatePayment(Payment payment);
    void deletePayment(Payment payment);
    Payment getPaymentById(int id);
}
