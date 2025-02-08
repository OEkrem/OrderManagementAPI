package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.Models.Order;
import com.oekrem.SpringMVCBackEnd.Services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    @Qualifier("orderModelMapper")
    private ModelMapper modelMapper;

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {this.orderService = orderService;}

    @GetMapping
    public List<OrderResponse> getOrders(){
        List<Order> orderList = orderService.findAll();
        return orderList.stream()
                .map(p->modelMapper.map(p, OrderResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        return modelMapper.map(order, OrderResponse.class);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody CreateOrderRequest addOrderRequest){
        Order order = modelMapper.map(addOrderRequest, Order.class);
        orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order){
        orderService.updateOrder(order);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
