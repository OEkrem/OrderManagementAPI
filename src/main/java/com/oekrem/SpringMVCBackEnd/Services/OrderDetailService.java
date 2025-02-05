package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Models.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findAll();
    void addOrderDetail(OrderDetail orderDetail);
    void updateOrderDetail(OrderDetail orderDetail);
    void deleteOrderDetail(Long id);
    OrderDetail getOrderDetailById(Long id);
}
