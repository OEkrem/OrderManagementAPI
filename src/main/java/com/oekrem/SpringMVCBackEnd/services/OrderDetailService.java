package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailResponse> findAll();
    CreateOrderDetailRequest addOrderDetail(Long orderId, CreateOrderDetailRequest createOrderDetailRequest);
    UpdateOrderDetailRequest updateOrderDetail(Long orderId, UpdateOrderDetailRequest updateOrderDetailRequest);
    void deleteOrderDetail(Long id);
    OrderDetailResponse getOrderDetailById(Long id);

    List<OrderDetailResponse>getOrderDetailsByOrderId(Long orderId);

    OrderDetail validateOrderDetail(Long id);
}
