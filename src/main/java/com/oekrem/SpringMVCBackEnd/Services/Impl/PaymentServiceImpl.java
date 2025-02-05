package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.PaymentRepository;
import com.oekrem.SpringMVCBackEnd.Models.Payment;
import com.oekrem.SpringMVCBackEnd.Services.PaymentService;
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
    public void deletePayment(Long id) {
        paymentRepository.deletePayment(id);
    }

    @Override
    @Transactional
    public Payment getPaymentById(Long id) {
        return paymentRepository.getPaymentById(id);
    }
}
