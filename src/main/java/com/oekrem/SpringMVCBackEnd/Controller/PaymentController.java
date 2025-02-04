package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Models.Payment;
import com.oekrem.SpringMVCBackEnd.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {this.paymentService = paymentService;}

    @GetMapping("/payments")
    public List<Payment> getPayments(){
        return paymentService.findAll();
    }

    @GetMapping("/payments/{id}")
    public Payment getPaymentById(@PathVariable int id){
        return paymentService.getPaymentById(id);
    }

    @PostMapping("/payment/add")
    public void addPayment(@RequestBody Payment payment){
        paymentService.addPayment(payment);
    }

    @PostMapping("/payment/update")
    public void updatePayment(@RequestBody Payment payment){
        paymentService.updatePayment(payment);
    }

    @PostMapping("/payment/delete")
    public void deletePayment(@RequestBody Payment payment){
        paymentService.deletePayment(payment);
    }
}
