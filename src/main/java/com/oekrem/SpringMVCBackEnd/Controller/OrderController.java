package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Models.Order;
import com.oekrem.SpringMVCBackEnd.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {this.orderService = orderService;}

    @GetMapping("/orders")
    public List<Order> getOrders(){
        return orderService.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable int id){
        return orderService.getOrderById(id);
    }

    @PostMapping("/order/add")
    public void addOrder(@RequestBody Order order){
        orderService.addOrder(order);
    }

    @PostMapping("/order/update")
    public void updateOrder(@RequestBody Order order){
        orderService.updateOrder(order);
    }

    @PostMapping("/order/delete")
    public void deleteOrder(@RequestBody Order order){
        orderService.deleteOrder(order);
    }
}
