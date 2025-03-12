package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.PatchPaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import com.oekrem.SpringMVCBackEnd.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<Page<PaymentResponse>> getPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) PaymentStatus paymentStatus
            ){
        return ResponseEntity.ok(paymentService.findAll(page, size, paymentStatus));
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long paymentId){
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

    @PutMapping("{paymentId}")
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Long paymentId, @RequestBody UpdatePaymentRequest updatePaymentRequest){
        return ResponseEntity.ok(paymentService.updatePayment(paymentId, updatePaymentRequest));
    }

    @PatchMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> patchPayment(@PathVariable Long paymentId, @RequestBody PatchPaymentRequest patchPaymentRequest){
        return ResponseEntity.ok(paymentService.patchPayment(paymentId, patchPaymentRequest));
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId){
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }

}
