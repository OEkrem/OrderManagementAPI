package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {this.orderService = orderService;}

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
    public ResponseEntity<CreateOrderRequest> addOrder(@PathVariable Long userId, @RequestBody CreateOrderRequest createOrderRequest){
        orderService.addOrder(userId, createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createOrderRequest);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateOrderRequest> updateOrder(@PathVariable Long userId, @RequestBody UpdateOrderRequest updateOrderRequest){
        orderService.updateOrder(userId, updateOrderRequest);
        return ResponseEntity.ok(updateOrderRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
