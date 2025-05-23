package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.*;
import com.oekrem.SpringMVCBackEnd.dto.Response.*;
import com.oekrem.SpringMVCBackEnd.dto.common.PageResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    PageResponse<OrderResponse> findAll(int page, int size, Long userId);
    PageResponse<OrderAllResponse> findAllOrders(int page, int size, Long userId, OrderStatus orderStatus);

    OrderResponse addOrder(CreateOrderRequest createOrderRequest);
    PaymentResponse savePayment(Long orderId, CreatePaymentRequest createPaymentRequest);
    OrderDetailsResponse saveOrderDetails(Long orderId, List<CreateOrderDetailRequest> createOrderDetailRequests);
    OrderDetailResponse saveOrderDetail(Long orderId, CreateOrderDetailRequest createOrderDetailRequests);
    OrderResponse confirmOrder(Long orderId);

    //OrderResponse updateOrder(Long userId, UpdateOrderRequest updateOrderRequest);
    OrderAllResponse patchOrder(Long orderId, PatchOrderRequest patchOrderRequest);

    void deleteOrder(Long id);
    void deleteAllOrders(List<Order> orders);
    OrderResponse getOrderById(Long id);

    Order validateOrder(Long id);

    // For scheduled
    List<Order> findByOrdersDateBefore(LocalDate date);
}
