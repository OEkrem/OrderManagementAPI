package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.Models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.Services.OrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailController {

    @Autowired
    @Qualifier("orderDetailModelMapper")
    private ModelMapper modelMapper;

    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {this.orderDetailService = orderDetailService;}

    @GetMapping
    public List<OrderDetailResponse> getOrderDetails(){
        List<OrderDetail> orderDetailList = orderDetailService.findAll();
        return orderDetailList.stream()
                .map(p-> modelMapper.map(p, OrderDetailResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderDetailResponse getOrderDetailById(@PathVariable Long id){
        OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);
        return modelMapper.map(orderDetail, OrderDetailResponse.class);
    }

    @PostMapping
    public ResponseEntity<OrderDetail> addOrderDetail(@RequestBody OrderDetail orderDetail){
        orderDetailService.addOrderDetail(orderDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetail orderDetail){
        orderDetailService.updateOrderDetail(orderDetail);
        return ResponseEntity.ok(orderDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id){
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }
}
