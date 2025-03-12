package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.services.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orderdetails")
@RequiredArgsConstructor
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<Page<OrderDetailResponse>> getOrderDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long orderId
    ){
        return ResponseEntity.ok(orderDetailService.findAll(page, size, orderId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailResponse> getOrderDetailById(@PathVariable Long id){
        return ResponseEntity.ok(orderDetailService.getOrderDetailById(id));
    }

    /*@PostMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailResponse> addOrderDetail(@PathVariable Long orderId,@RequestBody CreateOrderDetailRequest createOrderDetailRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailService.addOrderDetail(orderId, createOrderDetailRequest));
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailResponse> updateOrderDetail(@PathVariable Long orderId, @RequestBody UpdateOrderDetailRequest updateOrderDetailRequest){
        return ResponseEntity.ok(orderDetailService.updateOrderDetail(orderId, updateOrderDetailRequest));
    }

    @PatchMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailResponse> patchOrderDetail(@PathVariable Long orderId, @RequestBody PatchOrderDetailRequest patchOrderDetailRequest){
        return ResponseEntity.ok(orderDetailService.patchOrderDetail(orderId, patchOrderDetailRequest));
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id){
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }

}
