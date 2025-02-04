package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.Models.User;

import java.util.List;

public interface OrderDetailRepository {
    List<OrderDetail> findAll();
    void addOrderDetail(OrderDetail orderDetail);
    void updateOrderDetail(OrderDetail orderDetail);
    void deleteOrderDetail(OrderDetail orderDetail);
    OrderDetail getOrderDetailById(int id);
}
