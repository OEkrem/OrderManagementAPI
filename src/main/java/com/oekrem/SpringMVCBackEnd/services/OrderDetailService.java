package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailResponse> findAll();
    OrderDetailResponse addOrderDetail(Long orderId, CreateOrderDetailRequest createOrderDetailRequest);
    OrderDetailResponse updateOrderDetail(Long orderId, UpdateOrderDetailRequest updateOrderDetailRequest);
    OrderDetailResponse patchOrderDetail(Long orderId, PatchOrderDetailRequest patchOrderDetailRequest);
    void deleteOrderDetail(Long id);
    OrderDetailResponse getOrderDetailById(Long id);

    List<OrderDetailResponse>getOrderDetailsByOrderId(Long orderId);

    OrderDetail validateOrderDetail(Long id);
}
