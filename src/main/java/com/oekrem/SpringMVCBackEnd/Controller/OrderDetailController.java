package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.Services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderDetailController {

    private OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {this.orderDetailService = orderDetailService;}

    @GetMapping("/orderdetails")
    public List<OrderDetail> getOrderDetails(){
        return orderDetailService.findAll();
    }

    @GetMapping("/orderdetails/{id}")
    public OrderDetail getOrderDetailById(@PathVariable int id){
        return orderDetailService.getOrderDetailById(id);
    }

    @PostMapping("/orderdetail/add")
    public void addOrderDetail(@RequestBody OrderDetail orderDetail){
        orderDetailService.addOrderDetail(orderDetail);
    }

    @PostMapping("/orderdetail/update")
    public void updateOrderDetail(@RequestBody OrderDetail orderDetail){
        orderDetailService.updateOrderDetail(orderDetail);
    }

    @PostMapping("/orderdetail/delete")
    public void deleteOrderDetail(@RequestBody OrderDetail orderDetail){
        orderDetailService.deleteOrderDetail(orderDetail);
    }
}
