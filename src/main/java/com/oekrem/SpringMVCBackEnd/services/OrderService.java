package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Page<OrderResponse> findAll(int page, int size, Long userId);
    Page<OrderAllResponse> findAllOrders(int page, int size, Long userId);
    OrderResponse addOrder(Long userId, CreateOrderRequest createOrderRequest);
    OrderResponse updateOrder(Long userId, UpdateOrderRequest updateOrderRequest);
    OrderAllResponse patchOrder(Long userId, PatchOrderRequest patchOrderRequest);
    void deleteOrder(Long id);
    void deleteAllOrders(List<Order> orders);
    OrderResponse getOrderById(Long id);

    Order validateOrder(Long id);

    // For scheduled
    List<Order> findByOrdersDateBefore(LocalDate date);
}
