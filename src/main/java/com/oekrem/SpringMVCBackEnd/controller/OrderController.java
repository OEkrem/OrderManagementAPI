package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.services.OrderService;
import lombok.RequiredArgsConstructor;
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
    public List<OrderResponse> getOrders(){
        return orderService.findAll();
    }

    @GetMapping("/all")
    public List<OrderAllResponse> getOrdersAll(){
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
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
