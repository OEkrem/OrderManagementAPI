package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.PaymentRepository;
import com.oekrem.SpringMVCBackEnd.Dto.Mapper.CustomMapper.PaymentMapper;
import com.oekrem.SpringMVCBackEnd.Dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.Exceptions.PaymentExceptions.PaymentNotFoundException;
import com.oekrem.SpringMVCBackEnd.Models.Order;
import com.oekrem.SpringMVCBackEnd.Models.Payment;
import com.oekrem.SpringMVCBackEnd.Services.OrderService;
import com.oekrem.SpringMVCBackEnd.Services.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderService orderService, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
        this.paymentMapper = paymentMapper;
    }

    @Override
    @Transactional
    public List<PaymentResponse> findAll() {
        List<Payment> payments = paymentRepository.findAll();
        return paymentRepository.findAll().stream().map(paymentMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CreatePaymentRequest addPayment(Long orderId, CreatePaymentRequest createPaymentRequest) {
        orderService.validateOrder(orderId);

        Payment payment = paymentMapper.toPaymentFromCreatePaymentRequest(createPaymentRequest);
        Order order = new Order(); order.setId(orderId);
        payment.setOrder(order);
        System.out.println(payment);
        paymentRepository.addPayment(payment);
        return createPaymentRequest;
    }

    @Override
    @Transactional
    public UpdatePaymentRequest updatePayment(Long orderId, UpdatePaymentRequest updatePaymentRequest) {
        orderService.validateOrder(orderId);

        Payment payment = paymentMapper.toPaymentFromUpdatePaymentRequest(updatePaymentRequest);
        Order order = new Order(); order.setId(orderId);
        payment.setOrder(order);
        paymentRepository.updatePayment(payment);
        return updatePaymentRequest;
    }

    @Override
    @Transactional
    public void deletePayment(Long id) {
        paymentRepository.deletePayment(id);
    }

    @Override
    @Transactional
    public PaymentResponse getPaymentById(Long id) {
        return paymentMapper.toResponse(validatePayment(id));
    }

    @Override
    public Payment validatePayment(Long id) {
        return paymentRepository.getPaymentById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    }


}
