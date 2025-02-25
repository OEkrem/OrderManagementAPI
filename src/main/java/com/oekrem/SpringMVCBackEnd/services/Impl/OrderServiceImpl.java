package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.repository.OrderRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.CustomMapper.OrderMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.OrderExceptions.OrderNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.services.OrderService;
import com.oekrem.SpringMVCBackEnd.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    @Transactional
    public List<OrderResponse> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toOrderResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<OrderAllResponse> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toOrderAllResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponse addOrder(Long id, CreateOrderRequest createOrderRequest) {
        userService.validateUser(id);

        User user = new User(); user.setId(id);
        Order order = orderMapper.toOrderFromCreateOrderRequest(createOrderRequest);
        order.setUser(user);

        Order savedOrder = orderRepository.addOrder(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    @Transactional
    public OrderResponse updateOrder(Long id, UpdateOrderRequest updateOrderRequest) {
        User user = userService.validateUser(id);
        Order orderValidated = validateOrder(updateOrderRequest.getId());

        Order order = orderMapper.toOrderFromUpdateOrderRequest(updateOrderRequest);
        order.setUser(user);
        order.setOrderDetail(orderValidated.getOrderDetail());
        order.setPayment(orderValidated.getPayment());
        Order updatedOrder = orderRepository.updateOrder(order);

        return orderMapper.toOrderResponse(updatedOrder);
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
        return orderMapper.toOrderResponse(validateOrder(id));
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
