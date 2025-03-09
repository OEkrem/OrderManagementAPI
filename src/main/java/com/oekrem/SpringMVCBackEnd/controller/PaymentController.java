package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchPaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import com.oekrem.SpringMVCBackEnd.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id){
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentByOrderId(@PathVariable Long orderId){
        return ResponseEntity.ok(paymentService.getPaymentByOrderId(orderId));
    }

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<PaymentResponse> addPayment(@PathVariable Long orderId, @RequestBody CreatePaymentRequest createPaymentRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.addPayment(orderId, createPaymentRequest));
    }

    @PutMapping("orders/{orderId}")
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Long orderId, @RequestBody UpdatePaymentRequest updatePaymentRequest){
        return ResponseEntity.ok(paymentService.updatePayment(orderId, updatePaymentRequest));
    }

    @PatchMapping("/orders/{orderId}")
    public ResponseEntity<PaymentResponse> patchPayment(@PathVariable Long orderId, @RequestBody PatchPaymentRequest patchPaymentRequest){
        return ResponseEntity.ok(paymentService.patchPayment(orderId, patchPaymentRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

}
