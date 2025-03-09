package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.models.Payment;
import com.oekrem.SpringMVCBackEnd.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderMapperUnitTest {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Test
    public void shouldMapOrderToOrderResponse(){
        Order order = Order.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .orderDetail(List.of(OrderDetail.builder().id(1L).build(),OrderDetail.builder().id(2L).build()))
                .payment(Payment.builder().id(1L).build())
                .date(LocalDate.now())
                .total(300D)
                .build();
        OrderResponse orderResponse = orderMapper.toResponse(order);

        assertEquals(order.getId(), orderResponse.getId());
        assertEquals(order.getUser().getId(), orderResponse.getUserId());
        assertEquals(order.getDate(), orderResponse.getDate());
        assertEquals(order.getTotal(), orderResponse.getTotal());
    }

    @Test
    public void shouldMapOrderToOrderAllResponse(){
        Order order = Order.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .orderDetail(List.of(OrderDetail.builder().id(1L).build(),OrderDetail.builder().id(2L).build()))
                .payment(Payment.builder().id(1L).build())
                .date(LocalDate.now())
                .total(300D)
                .build();
        List<OrderDetailResponse> orderDetailResponseList = orderDetailMapper.toResponseList(order.getOrderDetail());

        OrderAllResponse response = orderMapper.toResponseAll(order);

        assertEquals(response.getId(), order.getId());
        assertEquals(response.getUserId(), order.getUser().getId());
        assertEquals(response.getDate(), order.getDate());
        assertEquals(response.getTotal(), order.getTotal());
        assertEquals(response.getOrderDetailResponses(), orderDetailResponseList);
        assertEquals(response.getPayment().getId(), order.getPayment().getId());

    }

    @Test
    public void shouldMapCreateRequestToOrder(){
        CreateOrderRequest orderRequest = CreateOrderRequest.builder()
                .date(LocalDate.now())
                .total(300D)
                .build();
        Order order = orderMapper.toOrderFromCreateRequest(orderRequest);

        assertNull(order.getId());
        assertNull(order.getUser());
        assertNull(order.getPayment());
        assertNull(order.getOrderDetail());
        assertEquals(order.getDate(), orderRequest.getDate());
        assertEquals(order.getTotal(), orderRequest.getTotal());
    }

    @Test
    public void shouldMapUpdateRequestToOrder(){
        UpdateOrderRequest orderRequest = UpdateOrderRequest.builder()
                .id(1L)
                .date(LocalDate.now())
                .total(300D)
                .build();
        Order order = orderMapper.toOrderFromUpdateRequest(orderRequest);

        assertEquals(order.getId(), orderRequest.getId());
        assertNull(order.getUser());
        assertNull(order.getPayment());
        assertNull(order.getOrderDetail());
        assertEquals(order.getDate(), orderRequest.getDate());
        assertEquals(order.getTotal(), orderRequest.getTotal());
    }

}
