package com.oekrem.SpringMVCBackEnd.dto.Mapper.CustomMapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.OrderExceptions.OrderMapperException;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private Order order;
    private User user;

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private PaymentMapper paymentMapper;


    public Order toOrderFromCreateOrderRequest(CreateOrderRequest createOrderRequest){
        order = new Order();
        order.setDate(createOrderRequest.getDate());
        order.setTotal(createOrderRequest.getTotal());
        return order;
    }

    public Order toOrderFromUpdateOrderRequest(UpdateOrderRequest updateOrderRequest){
        order = new Order();
        order.setId(updateOrderRequest.getId());
        order.setTotal(updateOrderRequest.getTotal());
        order.setDate(updateOrderRequest.getDate());
        return order;
    }

    public OrderResponse toOrderResponse(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUser().getId());
        orderResponse.setTotal(order.getTotal());
        orderResponse.setDate(order.getDate());
        return orderResponse;
    }

    public Order toOrderFromOrderResponse(OrderResponse orderResponse){
        user = new User(); user.setId(orderResponse.getUserId());
        order = new Order();
        order.setId(orderResponse.getId());
        order.setTotal(orderResponse.getTotal());
        order.setDate(orderResponse.getDate());
        order.setUser(user);
        return order;
    }

    public Order toOrderFromOrderAllResponse(OrderAllResponse orderAllResponse){
        try {
            user = new User();
            user.setId(orderAllResponse.getUserId());
            Order order = new Order();
            order.setId(orderAllResponse.getId());
            order.setTotal(orderAllResponse.getTotal());
            order.setDate(orderAllResponse.getDate());
            order.setUser(user);
            order.setPayment(paymentMapper.toPaymentFromResponse(orderAllResponse.getPayment()));
            order.setOrderDetail(
                    orderAllResponse.getOrderDetailIdList().stream()
                            .map(u -> orderDetailMapper.toOrderDetailFromOrderDetailResponse(u))
                            .collect(Collectors.toList())
            );
            return order;
        }catch (Exception e){
            throw new OrderMapperException("OrderMapper toOrderFromOrderAllResponse failed");
        }
    }

    public OrderAllResponse toOrderAllResponse(Order order){
        try {
            OrderAllResponse orderAllResponse = new OrderAllResponse();
            orderAllResponse.setId(order.getId());
            orderAllResponse.setTotal(order.getTotal());
            orderAllResponse.setDate(order.getDate());
            orderAllResponse.setUserId(order.getUser().getId());

            if (order.getOrderDetail() != null)
                orderAllResponse.setOrderDetailIdList(order.getOrderDetail().stream().map(orderDetailMapper::toResponse).collect(Collectors.toList()));
            else
                orderAllResponse.setOrderDetailIdList(new LinkedList<OrderDetailResponse>());

            if (order.getPayment() != null)
                orderAllResponse.setPayment(paymentMapper.toResponse(order.getPayment()));
            else
                orderAllResponse.setPayment(new PaymentResponse());
            return orderAllResponse;
        }catch(NullPointerException e){
            throw new OrderMapperException("OrderMapper toOrderAllResponse failed");
        }
    }
}
