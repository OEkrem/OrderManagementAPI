package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.OrderDetailRepository;
import com.oekrem.SpringMVCBackEnd.Models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.Services.OrderDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {this.orderDetailRepository = orderDetailRepository;}

    @Override
    @Transactional
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    @Transactional
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.addOrderDetail(orderDetail);
    }

    @Override
    @Transactional
    public void updateOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.updateOrderDetail(orderDetail);
    }

    @Override
    @Transactional
    public void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteOrderDetail(id);
    }

    @Override
    @Transactional
    public OrderDetail getOrderDetailById(Long id) {
        return orderDetailRepository.getOrderDetailById(id);
    }
}
