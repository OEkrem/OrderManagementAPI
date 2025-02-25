package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.services.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderdetails")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @GetMapping
    public List<OrderDetailResponse> getOrderDetails(){
        return orderDetailService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDetailResponse getOrderDetailById(@PathVariable Long id){
        return orderDetailService.getOrderDetailById(id);
    }

    @GetMapping("/orders/{orderId}")
    public List<OrderDetailResponse> getOrderDetailsByOrderId(@PathVariable Long orderId){
        return orderDetailService.getOrderDetailsByOrderId(orderId);
    }

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailResponse> addOrderDetail(@PathVariable Long orderId,@RequestBody CreateOrderDetailRequest createOrderDetailRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailService.addOrderDetail(orderId, createOrderDetailRequest));
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailResponse> updateOrderDetail(@PathVariable Long orderId, @RequestBody UpdateOrderDetailRequest updateOrderDetailRequest){
        return ResponseEntity.ok(orderDetailService.updateOrderDetail(orderId, updateOrderDetailRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id){
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }
}
