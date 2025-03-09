package com.oekrem.SpringMVCBackEnd.repository;

import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository {
    Page<OrderDetail> findAll(Pageable pageable);
    Page<OrderDetail> findByOrderId(Pageable pageable, Long orderId);
    OrderDetail addOrderDetail(OrderDetail orderDetail);
    OrderDetail updateOrderDetail(OrderDetail orderDetail);
    void deleteOrderDetail(Long id);
    Optional<OrderDetail> getOrderDetailById(Long id);

    List<OrderDetail> getOrderDetailsByOrderId(Long orderId);
}
