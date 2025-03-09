package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
import org.springframework.data.domain.Page;

public interface OrderDetailService {
    Page<OrderDetailResponse> findAll(int page, int size, Long userId);
    OrderDetailResponse addOrderDetail(Long orderId, CreateOrderDetailRequest createOrderDetailRequest);
    OrderDetailResponse updateOrderDetail(Long orderId, UpdateOrderDetailRequest updateOrderDetailRequest);
    OrderDetailResponse patchOrderDetail(Long orderId, PatchOrderDetailRequest patchOrderDetailRequest);
    void deleteOrderDetail(Long id);
    OrderDetailResponse getOrderDetailById(Long id);

    OrderDetail validateOrderDetail(Long id);
}
