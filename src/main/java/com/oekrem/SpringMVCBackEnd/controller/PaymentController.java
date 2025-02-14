package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public List<PaymentResponse> getPayments(){
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id){
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/orders/{orderId}")
    public PaymentResponse getPaymentByOrderId(@PathVariable Long orderId){
        return paymentService.getPaymentByOrderId(orderId);
    }

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<CreatePaymentRequest> addPayment(@PathVariable Long orderId, @RequestBody CreatePaymentRequest createPaymentRequest){
        paymentService.addPayment(orderId, createPaymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPaymentRequest);
    }

    @PutMapping("orders/{orderId}")
    public ResponseEntity<UpdatePaymentRequest> updatePayment(@PathVariable Long orderId, @RequestBody UpdatePaymentRequest updatePaymentRequest){
        paymentService.updatePayment(orderId, updatePaymentRequest);
        return ResponseEntity.ok(updatePaymentRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
