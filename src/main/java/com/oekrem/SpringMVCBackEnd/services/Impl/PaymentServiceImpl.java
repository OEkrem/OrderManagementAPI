package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.repository.PaymentRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.CustomMapper.PaymentMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.PaymentExceptions.PaymentNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.Payment;
import com.oekrem.SpringMVCBackEnd.services.OrderService;
import com.oekrem.SpringMVCBackEnd.services.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    @Override
    @Transactional
    public List<PaymentResponse> findAll() {
        return paymentRepository.findAll().stream().map(paymentMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PaymentResponse addPayment(Long orderId, CreatePaymentRequest createPaymentRequest) {
        orderService.validateOrder(orderId);

        Payment payment = paymentMapper.toPaymentFromCreatePaymentRequest(createPaymentRequest);
        Order order = new Order(); order.setId(orderId);
        payment.setOrder(order);

        Payment savedPayment = paymentRepository.addPayment(payment);
        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    @Transactional
    public PaymentResponse updatePayment(Long orderId, UpdatePaymentRequest updatePaymentRequest) {
        orderService.validateOrder(orderId);

        Payment payment = paymentMapper.toPaymentFromUpdatePaymentRequest(updatePaymentRequest);
        Order order = new Order(); order.setId(orderId);
        payment.setOrder(order);

        Payment  updatedPayment = paymentRepository.updatePayment(payment);
        return paymentMapper.toResponse(updatedPayment);
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
    public PaymentResponse getPaymentByOrderId(Long orderId) {
        return paymentMapper.toResponse(validatePaymentByOrderId(orderId));
    }

    @Override
    public Payment validatePayment(Long id) {
        return paymentRepository.getPaymentById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    }

    @Override
    public Payment validatePaymentByOrderId(Long orderId) {
        return paymentRepository.getPaymentByOrderId(orderId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found by orderId"));
    }


}
