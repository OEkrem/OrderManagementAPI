package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.*;
import com.oekrem.SpringMVCBackEnd.dto.Response.*;
import com.oekrem.SpringMVCBackEnd.dto.common.PageResponse;
import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;
import com.oekrem.SpringMVCBackEnd.repository.OrderRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.OrderMapper;
import com.oekrem.SpringMVCBackEnd.exceptions.OrderExceptions.OrderNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.services.*;
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
    private final PaymentService paymentService;
    private final OrderDetailService orderDetailService;

    @Override
    @Transactional
    public PageResponse<OrderResponse> findAll(int page, int size, Long userId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders;
        if(userId != null)
            orders = orderRepository.findAllByUserId(pageable, userId);
        else
            orders = orderRepository.findAll(pageable);
        Page<OrderResponse> responsesPage = orders.map(orderMapper::toResponse);
        return PageResponse.fromPage(responsesPage);
    }

    @Override
    @Transactional
    public PageResponse<OrderAllResponse> findAllOrders(int page, int size, Long userId, OrderStatus orderStatus) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders;
        if(userId != null && orderStatus != null)
            orders = orderRepository.findAllByUserIdAndOrderStatus(pageable, userId, orderStatus);
        else if(userId != null)
            orders = orderRepository.findAllByUserId(pageable, userId);
        else if(orderStatus != null)
            orders = orderRepository.findAllByOrderStatus(pageable, orderStatus);
        else
            orders = orderRepository.findAll(pageable);
        Page<OrderAllResponse> responsesPage = orders.map(orderMapper::toResponseAll);
        return PageResponse.fromPage(responsesPage);
    }

    @Override
    @Transactional
    public OrderResponse addOrder(CreateOrderRequest createOrderRequest) {
        User user = userService.validateUser(createOrderRequest.userId());
        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.addOrder(order);
        System.out.println("SavedOrder: " + savedOrder.getId() + " " + savedOrder.getOrderStatus() + " " + savedOrder.getDate());
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
    public PaymentResponse savePayment(Long orderId, CreatePaymentRequest createPaymentRequest) {
        Order order = validateOrder(orderId);
        return paymentService.addPayment(order, createPaymentRequest);
    }

    @Override
    public OrderDetailResponse saveOrderDetail(Long orderId, CreateOrderDetailRequest createOrderDetailRequests) {
        Order order = validateOrder(orderId);
        return orderDetailService.addOrderDetail(order, createOrderDetailRequests);
    }

    @Override
    public OrderDetailsResponse saveOrderDetails(Long orderId, List<CreateOrderDetailRequest> createOrderDetailRequests) {
        Order order = validateOrder(orderId);
        return orderDetailService.addOrderDetails(order, createOrderDetailRequests);
    }

    @Override
    public OrderResponse confirmOrder(Long orderId) {
        Order order = validateOrder(orderId);
        order.setOrderStatus(OrderStatus.APPROVED);
        return orderMapper.toResponse(orderRepository.updateOrder(order));
    }

    @Override
    public OrderAllResponse patchOrder(Long orderId, PatchOrderRequest patchOrderRequest) {
        Order orderValidated = validateOrder(orderId);

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
