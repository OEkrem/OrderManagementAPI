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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final AmqpTemplate amqpTemplate; // RabbitMQ ile

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    @Transactional
    public List<OrderResponse> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<OrderAllResponse> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toResponseAll).collect(Collectors.toList());
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
