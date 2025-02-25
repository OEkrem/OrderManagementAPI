package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<OrderResponse> findAll();
    List<OrderAllResponse> findAllOrders();
    OrderResponse addOrder(Long id, CreateOrderRequest createOrderRequest);
    OrderResponse updateOrder(Long id, UpdateOrderRequest updateOrderRequest);
    void deleteOrder(Long id);
    void deleteAllOrders(List<Order> orders);
    OrderResponse getOrderById(Long id);

    Order validateOrder(Long id);

    // For scheduled
    List<Order> findByOrdersDateBefore(LocalDate date);
}
