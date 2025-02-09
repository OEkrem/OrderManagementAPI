package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.Dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.Models.Order;

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
