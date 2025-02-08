package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.Models.OrderDetail;

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
