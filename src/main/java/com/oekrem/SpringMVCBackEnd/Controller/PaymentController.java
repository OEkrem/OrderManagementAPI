package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {this.paymentService = paymentService;}

    @GetMapping
    public List<PaymentResponse> getPayments(){
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id){
        return paymentService.getPaymentById(id);
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
