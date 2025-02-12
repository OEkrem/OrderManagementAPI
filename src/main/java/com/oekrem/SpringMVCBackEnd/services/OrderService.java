package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;

import java.util.List;

public interface OrderService {
    List<OrderResponse> findAll();
    List<OrderAllResponse> findAllOrders();
    CreateOrderRequest addOrder(Long id, CreateOrderRequest createOrderRequest);
    UpdateOrderRequest updateOrder(Long id, UpdateOrderRequest updateOrderRequest);
    void deleteOrder(Long id);
    OrderResponse getOrderById(Long id);

    Order validateOrder(Long id);
}
