package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.PatchOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.repository.OrderDetailRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.OrderDetailMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.OrderDetailExceptions.OrderDetailNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.services.OrderDetailService;
import com.oekrem.SpringMVCBackEnd.services.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailMapper orderDetailMapper;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderService orderService;

    @Override
    @Transactional
    public List<OrderDetailResponse> findAll() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream()
                .map(orderDetailMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDetailResponse addOrderDetail(Long orderId, CreateOrderDetailRequest createOrderDetailRequest) {
        orderService.validateOrder(orderId);

        OrderDetail orderDetail = orderDetailMapper.toOrderDetailFromCreateRequest(createOrderDetailRequest);
        Order order = new Order(); order.setId(orderId);
        orderDetail.setOrder(order);

        OrderDetail savedOrderDetail = orderDetailRepository.addOrderDetail(orderDetail);
        return orderDetailMapper.toResponse(savedOrderDetail);
    }

    @Override
    @Transactional
    public OrderDetailResponse updateOrderDetail(Long orderId ,UpdateOrderDetailRequest updateOrderDetailRequest) {
        orderService.validateOrder(orderId);
        validateOrderDetail(updateOrderDetailRequest.getId());

        OrderDetail orderDetail = orderDetailMapper.toOrderDetailFromUpdateRequest(updateOrderDetailRequest);
        Order order = new Order(); order.setId(orderId);
        orderDetail.setOrder(order);

        OrderDetail updatedOrderDetail = orderDetailRepository.updateOrderDetail(orderDetail);
        return orderDetailMapper.toResponse(updatedOrderDetail);
    }

    @Override
    @Transactional
    public OrderDetailResponse patchOrderDetail(Long orderId, PatchOrderDetailRequest patchOrderDetailRequest) {
        orderService.validateOrder(orderId);
        OrderDetail orderDetail = validateOrderDetail(patchOrderDetailRequest.id());

        orderDetailMapper.patchOrderDetail(patchOrderDetailRequest, orderDetail);
        OrderDetail savedOrderDetail = orderDetailRepository.updateOrderDetail(orderDetail);
        return orderDetailMapper.toResponse(savedOrderDetail);
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
                .map(orderDetailMapper::toResponse)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public OrderDetail validateOrderDetail(Long id){
        return orderDetailRepository.getOrderDetailById(id)
                .orElseThrow(() -> new OrderDetailNotFoundException("OrderDetail not found with id: " + id));
    }
}
