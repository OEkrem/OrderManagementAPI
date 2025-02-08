package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository {
    List<OrderDetail> findAll();
    OrderDetail addOrderDetail(OrderDetail orderDetail);
    OrderDetail updateOrderDetail(OrderDetail orderDetail);
    void deleteOrderDetail(Long id);
    Optional<OrderDetail> getOrderDetailById(Long id);

    List<OrderDetail> getOrderDetailsByOrderId(Long orderId);
}
