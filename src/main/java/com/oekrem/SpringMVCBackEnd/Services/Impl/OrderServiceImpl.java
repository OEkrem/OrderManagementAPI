package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.OrderRepository;
import com.oekrem.SpringMVCBackEnd.Dto.Mapper.CustomMapper.OrderMapper;
import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.Dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.Exceptions.OrderExceptions.OrderNotFoundException;
import com.oekrem.SpringMVCBackEnd.Models.Order;
import com.oekrem.SpringMVCBackEnd.Models.User;
import com.oekrem.SpringMVCBackEnd.Services.OrderService;
import com.oekrem.SpringMVCBackEnd.Services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, UserService userService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userService = userService;
    }

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
    public CreateOrderRequest addOrder(Long id, CreateOrderRequest createOrderRequest) {
        userService.validateUser(id);

        User user = new User(); user.setId(id);
        Order order = orderMapper.toOrderFromCreateOrderRequest(createOrderRequest);
        order.setUser(user);
        System.out.println(order);
        orderRepository.addOrder(order);
        return createOrderRequest;
    }

    @Override
    @Transactional
    public UpdateOrderRequest updateOrder(Long id, UpdateOrderRequest updateOrderRequest) {
        User user = userService.validateUser(id);
        Order orderValidated = validateOrder(updateOrderRequest.getId());

        Order order = orderMapper.toOrderFromUpdateOrderRequest(updateOrderRequest);
        order.setUser(user);
        order.setOrderDetail(orderValidated.getOrderDetail());
        order.setPayment(orderValidated.getPayment());
        orderRepository.updateOrder(order);
        return updateOrderRequest;
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteOrder(id);
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
}
