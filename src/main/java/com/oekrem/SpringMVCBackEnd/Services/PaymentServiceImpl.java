package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.DataAccess.PaymentRepository;
import com.oekrem.SpringMVCBackEnd.Models.Payment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {this.paymentRepository = paymentRepository;}

    @Override
    @Transactional
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    @Transactional
    public void addPayment(Payment payment) {
        paymentRepository.addPayment(payment);
    }

    @Override
    @Transactional
    public void updatePayment(Payment payment) {
        paymentRepository.updatePayment(payment);
    }

    @Override
    @Transactional
    public void deletePayment(Payment payment) {
        paymentRepository.deletePayment(payment);
    }

    @Override
    @Transactional
    public Payment getPaymentById(int id) {
        return paymentRepository.getPaymentById(id);
    }
}
