package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.PatchOrderRequest;
import com.oekrem.SpringMVCBackEnd.repository.OrderRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.OrderMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.OrderExceptions.OrderNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.services.OrderService;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import com.oekrem.SpringMVCBackEnd.services.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final AmqpTemplate amqpTemplate; // RabbitMQ ile

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Page<OrderResponse> findAll(int page, int size, Long userId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders;
        if(userId != null)
            orders = orderRepository.findAllByUserId(pageable, userId);
        else
            orders = orderRepository.findAll(pageable);

        return orders.map(orderMapper::toResponse);
    }

    @Override
    @Transactional
    public Page<OrderAllResponse> findAllOrders(int page, int size, Long userId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders;
        if(userId != null)
            orders = orderRepository.findAllByUserId(pageable, userId);
        else
            orders = orderRepository.findAll(pageable);
        return orders.map(orderMapper::toResponseAll);
    }

    @Override
    @Transactional
    public OrderResponse addOrder(Long userId, CreateOrderRequest createOrderRequest) {
        User user = userService.validateUser(userId);
        Order order = orderMapper.toOrderFromCreateRequest(createOrderRequest);
        order.setUser(user);

        Order savedOrder = orderRepository.addOrder(order);
        // sipariş oluşturuldu
        // Olay nesnesi
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                .customerId(user.getId())
                .orderId(savedOrder.getId())
                .build();
        amqpTemplate.convertAndSend("orderQueue", orderCreatedEvent);

        return orderMapper.toResponse(savedOrder);
    }

    @Override
    @Transactional
    public OrderResponse updateOrder(Long userId, UpdateOrderRequest updateOrderRequest) {
        User user = userService.validateUser(userId);
        Order orderValidated = validateOrder(updateOrderRequest.getId());

        Order order = orderMapper.toOrderFromUpdateRequest(updateOrderRequest);
        order.setUser(user);
        order.setOrderDetail(orderValidated.getOrderDetail());
        order.setPayment(orderValidated.getPayment());
        Order updatedOrder = orderRepository.updateOrder(order);

        return orderMapper.toResponse(updatedOrder);
    }

    @Override
    public OrderAllResponse patchOrder(Long userId, PatchOrderRequest patchOrderRequest) {
        User user = userService.validateUser(userId);
        Order orderValidated = validateOrder(patchOrderRequest.id());

        orderMapper.patchOrder(patchOrderRequest, orderValidated);
        Order savedORder = orderRepository.updateOrder(orderValidated);
        return orderMapper.toResponseAll(savedORder);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteOrder(id);
    }

    @Override
    public void deleteAllOrders(List<Order> orders) {
        orderRepository.deleteAllOrders(orders);
    }

    @Override
    @Transactional
    public OrderResponse getOrderById(Long id) {
        return orderMapper.toResponse(validateOrder(id));
    }

    @Override
    public Order validateOrder(Long id) {
        return orderRepository.getOrderById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));
    }

    @Override
    public List<Order> findByOrdersDateBefore(LocalDate date) {
        return orderRepository.findByOrdersDateBefore(date);
    }
}
