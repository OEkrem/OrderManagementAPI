package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long userId
            //@RequestParam(required = false) PaymentStatus paymentStatus
            ){
        return ResponseEntity.ok(orderService.findAll(page, size, userId));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<OrderAllResponse>> getOrdersAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long userId
    ){
        return ResponseEntity.ok(orderService.findAllOrders(page, size, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<OrderResponse> addOrder(@PathVariable Long userId, @RequestBody CreateOrderRequest createOrderRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(userId, createOrderRequest));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Long userId, @RequestBody UpdateOrderRequest updateOrderRequest){
        return ResponseEntity.ok(orderService.updateOrder(userId, updateOrderRequest));
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<OrderAllResponse> patchOrder(@PathVariable Long userId, @RequestBody PatchOrderRequest patchOrderRequest){
        return ResponseEntity.ok(orderService.patchOrder(userId, patchOrderRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
