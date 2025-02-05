package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Models.Payment;
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
    public List<Payment> getPayments(){
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id){
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    public ResponseEntity<Payment> addPayment(@RequestBody Payment payment){
        paymentService.addPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment payment){
        paymentService.updatePayment(payment);
        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
