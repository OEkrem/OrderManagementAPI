package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.OrderRepository;
import com.oekrem.SpringMVCBackEnd.Models.Order;
import com.oekrem.SpringMVCBackEnd.Services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    @Override
    @Transactional
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteOrder(id);
    }

    @Override
    @Transactional
    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id);
    }
}
