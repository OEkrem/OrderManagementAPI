package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.*;
import com.oekrem.SpringMVCBackEnd.dto.Response.*;
import com.oekrem.SpringMVCBackEnd.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderAllResponse>> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long userId
    ){
        return ResponseEntity.ok(orderService.findAllOrders(page, size, userId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> addOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(createOrderRequest));
    }

    @PatchMapping("/{orderId}/payment")
    public ResponseEntity<PaymentResponse> addPayment(@PathVariable Long orderId, @RequestBody CreatePaymentRequest createPaymentRequest){
        return ResponseEntity.ok(orderService.savePayment(orderId, createPaymentRequest));
    }

    @PatchMapping("/{orderId}/orderdetail")
    public ResponseEntity<OrderDetailResponse> addOrderDetail(@PathVariable Long orderId, @RequestBody CreateOrderDetailRequest createOrderDetailRequests) {
        return ResponseEntity.ok(orderService.saveOrderDetail(orderId, createOrderDetailRequests));
    }

    @PatchMapping("/{orderId}/orderdetails")
    public ResponseEntity<OrderDetailsResponse> addOrderDetails(@PathVariable Long orderId, @RequestBody List<CreateOrderDetailRequest> createOrderDetailRequests) {
        return ResponseEntity.ok(orderService.saveOrderDetails(orderId, createOrderDetailRequests));
    }

    @PatchMapping("/{orderId}/confirm")
    public ResponseEntity<OrderResponse> confirmOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.confirmOrder(orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

}
