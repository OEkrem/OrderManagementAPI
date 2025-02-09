package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.OrderDetailRepository;
import com.oekrem.SpringMVCBackEnd.Dto.Mapper.CustomMapper.OrderDetailMapper;
import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.Exceptions.OrderDetailExceptions.OrderDetailNotFoundException;
import com.oekrem.SpringMVCBackEnd.Models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.Services.OrderDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    @Transactional
    public List<OrderDetailResponse> findAll() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream()
                .map(u-> orderDetailMapper.toResponse(u))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CreateOrderDetailRequest addOrderDetail(Long orderId, CreateOrderDetailRequest createOrderDetailRequest) {
        // validate orderId
        orderDetailRepository.addOrderDetail(orderDetailMapper.toOrderDetailFromCreateOrderDetailRequest(createOrderDetailRequest));
        return createOrderDetailRequest;
    }

    @Override
    @Transactional
    public UpdateOrderDetailRequest updateOrderDetail(Long orderId ,UpdateOrderDetailRequest updateOrderDetailRequest) {
        // validate orderId
        validateOrderDetail(updateOrderDetailRequest.getId());
        orderDetailRepository.updateOrderDetail(orderDetailMapper.toOrderDetailFromUpdateOrderDetailRequest(updateOrderDetailRequest));
        return updateOrderDetailRequest;
    }

    @Override
    @Transactional
    public void deleteOrderDetail(Long id) {
        validateOrderDetail(id);
        orderDetailRepository.deleteOrderDetail(id);
    }

    @Override
    @Transactional
    public OrderDetailResponse getOrderDetailById(Long id) {
        return orderDetailMapper.toResponse(validateOrderDetail(id));
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailsByOrderId(Long orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailsByOrderId(orderId);
        return orderDetails.stream()
                .map(u-> orderDetailMapper.toResponse(u))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public OrderDetail validateOrderDetail(Long id){
        return orderDetailRepository.getOrderDetailById(id)
                .orElseThrow(() -> new OrderDetailNotFoundException("OrderDetail not found with id: " + id));
    }
}
