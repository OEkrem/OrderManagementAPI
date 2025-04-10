package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.PatchPaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PaymentRequest;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import com.oekrem.SpringMVCBackEnd.repository.PaymentRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.PaymentMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.PaymentExceptions.PaymentNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Payment;
import com.oekrem.SpringMVCBackEnd.services.PaymentService;
import com.oekrem.SpringMVCBackEnd.services.bank.BinLookupService;
import com.oekrem.SpringMVCBackEnd.services.bank.GarantiBankasiPaymentService;
import com.oekrem.SpringMVCBackEnd.services.bank.IsBankasiPaymentService;
import com.oekrem.SpringMVCBackEnd.services.bank.enums.BankType;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;

    private final BinLookupService binLookupService;
    private final IsBankasiPaymentService isBankasiPaymentService;
    private final GarantiBankasiPaymentService garantiBankasiPaymentService;

    @Override
    @Transactional
    public Page<PaymentResponse> findAll(int page, int size, PaymentStatus status) {
        Pageable pageable = PageRequest.of(page, size);
        if(status != null)
            return paymentRepository.findByPaymentStatus(pageable, status).map(paymentMapper::toResponse);
        else
            return paymentRepository.findAll(pageable).map(paymentMapper::toResponse);
    }

    @Override
    @Transactional
    public PaymentResponse addPayment(Order order, CreatePaymentRequest createPaymentRequest) {
        Payment payment = paymentMapper.toPaymentFromCreateRequest(createPaymentRequest);
        payment.setOrder(order);

        payment.setAmount(order.getOrderDetails().stream().mapToDouble(OrderDetail::getPrice).sum());
        order.setTotal(payment.getAmount());

        Payment savedPayment = paymentRepository.addPayment(payment);
        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    @Transactional
    public PaymentResponse updatePayment(Long paymentId, UpdatePaymentRequest updatePaymentRequest) {
        validatePayment(paymentId);

        Payment payment = paymentMapper.toPaymentFromUpdateRequest(updatePaymentRequest);
        Payment  updatedPayment = paymentRepository.updatePayment(payment);
        return paymentMapper.toResponse(updatedPayment);
    }

    @Override
    @Transactional
    public PaymentResponse patchPayment(Long paymentId, PatchPaymentRequest patchPayment) {
        Payment patch = validatePayment(paymentId);

        paymentMapper.patchPayment(patchPayment, patch);
        Payment savedPayment = paymentRepository.updatePayment(patch);
        return paymentMapper.toResponse(savedPayment);
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

    @Override
    public boolean processPayment(PaymentRequest request) {
        BankType bankType = binLookupService.getBankByBin(request.cardNumber().substring(0, 6));

        return switch (bankType) {
            case GARANTI_BANK -> garantiBankasiPaymentService.processPayment(request);
            case IS_BANK -> isBankasiPaymentService.processPayment(request);
            case YAPI_KREDI -> throw new RuntimeException("Yapı Kredi şu an desteklenmiyor!");
            default -> throw new RuntimeException("Desteklenmeyen banka: " + bankType);
        };
    }

}
