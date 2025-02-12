package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailController {

    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {this.orderDetailService = orderDetailService;}

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
    public ResponseEntity<CreateOrderDetailRequest> addOrderDetail(@PathVariable Long orderId,@RequestBody CreateOrderDetailRequest createOrderDetailRequest){
        orderDetailService.addOrderDetail(orderId, createOrderDetailRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createOrderDetailRequest);
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<UpdateOrderDetailRequest> updateOrderDetail(@PathVariable Long orderId, @RequestBody UpdateOrderDetailRequest updateOrderDetailRequest){
        orderDetailService.updateOrderDetail(orderId, updateOrderDetailRequest);
        return ResponseEntity.ok(updateOrderDetailRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id){
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }
}
