package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Order;
import com.oekrem.SpringMVCBackEnd.Models.User;

import java.util.List;

public interface OrderRepository {
    List<Order> findAll();
    void addOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(Order order);
    Order getOrderById(int id);
}
