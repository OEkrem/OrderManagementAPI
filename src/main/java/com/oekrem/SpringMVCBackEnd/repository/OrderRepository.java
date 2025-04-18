package com.oekrem.SpringMVCBackEnd.repository;

import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Page<Order> findAllByUserIdAndOrderStatus(Pageable pageable, Long userId, OrderStatus orderStatus);
    Page<Order> findAllByUserId(Pageable pageable, Long userId);
    Page<Order> findAllByOrderStatus(Pageable pageable, OrderStatus orderStatus);
    Page<Order> findAll(Pageable pageable);
    Order addOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(Long id);
    void deleteAllOrders(List<Order> orders);
    Optional<Order> getOrderById(Long id);

    Optional<User> getOwnerById(Long id);

    // For scheduled
    List<Order> findByOrdersDateBefore(LocalDate date);
}
