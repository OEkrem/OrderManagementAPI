package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailsResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderDetailService {
    Page<OrderDetailResponse> findAll(int page, int size, Long userId);

    OrderDetailsResponse addOrderDetails(Order order, List<CreateOrderDetailRequest> createOrderDetailRequest);
    OrderDetailResponse addOrderDetail(Order order, CreateOrderDetailRequest createOrderDetailRequest);

    OrderDetailResponse updateOrderDetail(Long orderDetailId, UpdateOrderDetailRequest updateOrderDetailRequest);
    OrderDetailResponse patchOrderDetail(Long orderDetailId, PatchOrderDetailRequest patchOrderDetailRequest);
    void deleteOrderDetail(Long id);
    OrderDetailResponse getOrderDetailById(Long id);

    OrderDetail validateOrderDetail(Long id);
}
