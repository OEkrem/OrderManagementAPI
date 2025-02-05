package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Models.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    void addOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(Long id);
    Order getOrderById(Long id);
}
