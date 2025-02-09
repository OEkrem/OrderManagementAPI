package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> findAll();
    Order addOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(Long id);
    Optional<Order> getOrderById(Long id);
}
