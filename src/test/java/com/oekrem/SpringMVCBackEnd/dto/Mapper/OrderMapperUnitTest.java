package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.*;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.models.Payment;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentMethod;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderMapperUnitTest {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private PaymentMapper paymentMapper;

    @Test
    public void shouldMapOrderToOrderResponse(){
        Order order = Order.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .orderDetails(List.of(OrderDetail.builder().id(1L).build(),OrderDetail.builder().id(2L).build()))
                .payment(Payment.builder().id(1L).build())
                .date(LocalDate.now())
                .total(300D)
                .build();
        OrderResponse orderResponse = orderMapper.toResponse(order);

        assertEquals(order.getId(), orderResponse.id());
        assertEquals(order.getUser().getId(), orderResponse.userId());
        assertEquals(order.getDate(), orderResponse.date());
    }

    @Test
    public void shouldMapOrderToOrderAllResponse(){
        Order order = Order.builder()
                .id(1L)
                .user(User.builder().id(1L).build())
                .orderDetails(List.of(OrderDetail.builder().id(1L).build(),OrderDetail.builder().id(2L).build()))
                .payment(Payment.builder().id(1L).build())
                .date(LocalDate.now())
                .total(300D)
                .build();
        List<OrderDetailResponse> orderDetailResponseList = orderDetailMapper.toResponseList(order.getOrderDetails());

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
                .build();
        Order order = orderMapper.toOrderFromCreateRequest(orderRequest);

        assertNull(order.getId());
        assertNull(order.getUser());
        assertNull(order.getPayment());
    }

    @Test
    public void shouldMapUpdateRequestToOrder(){
        UpdateOrderRequest orderRequest = UpdateOrderRequest.builder()
                .id(1L)
                .build();
        Order order = orderMapper.toOrderFromUpdateRequest(orderRequest);

        assertEquals(order.getId(), orderRequest.id());
        assertNull(order.getUser());
        assertNull(order.getPayment());
        assertNull(order.getOrderDetails());
    }

    @Test
    public void shouldMapPatchOrderRequestToOrder(){
        PatchOrderRequest orderRequest = PatchOrderRequest.builder()
                .payment(CreatePaymentRequest.builder()
                        .paymentMethod(PaymentMethod.UNKNOWN)
                        .paymentStatus(PaymentStatus.PENDING)
                        .build())
                .orderDetails(List.of(
                        CreateOrderDetailRequest.builder().productId(1L).quantity(1).quantityType(QuantityType.BOX).build(),
                        CreateOrderDetailRequest.builder().productId(2L).quantity(1).quantityType(QuantityType.BOX).build()
                ))
                .build();
        Order order = Order.builder().build();

        orderMapper.patchOrder(orderRequest, order);
        System.out.println(orderRequest.orderDetails());

        assertEquals(
                orderRequest.orderDetails().stream().map(orderDetailMapper::toOrderDetailFromCreateRequest).collect(Collectors.toList()),
                order.getOrderDetails()
        );
        assertEquals(paymentMapper.toPaymentFromCreateRequest(orderRequest.payment()), order.getPayment());
    }

}
