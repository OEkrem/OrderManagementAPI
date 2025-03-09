package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.models.Product;
import com.oekrem.SpringMVCBackEnd.models.User;
import com.oekrem.SpringMVCBackEnd.models.enums.QuantityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDetailMapperUnitTest {

    private OrderDetailMapper orderDetailMapper;

    @BeforeEach
    public void setUp() {
        orderDetailMapper = Mappers.getMapper(OrderDetailMapper.class);
    }

    @Test
    public void shouldMapOrderDetailToOrderDetailResponse() {
        OrderDetail orderDetail = OrderDetail.builder()
                .id(1L)
                .price(200D)
                .product(Product.builder().id(1L).name("ff").price(200D).description("tt").image("rr").build())
                .order(Order.builder().id(1L).user(User.builder().id(1L).build()).build())
                .quantity(BigDecimal.valueOf(1))
                .quantityType(QuantityType.PACK)
                .build();

        OrderDetailResponse response = orderDetailMapper.toResponse(orderDetail);

        assertEquals(orderDetail.getId(), response.getId());
        assertEquals(orderDetail.getPrice(), response.getPrice());
        assertEquals(orderDetail.getOrder().getId(), response.getOrderId());
        assertEquals(orderDetail.getProduct().getId(), response.getProductId());
        assertEquals(orderDetail.getQuantity(), response.getQuantity());
        assertEquals(orderDetail.getQuantityType(), response.getQuantityType());

    }

    @Test
    public void shouldMapCreateOrderDetailToOrderDetail() {
        CreateOrderDetailRequest createOrderDetailRequest = CreateOrderDetailRequest.builder()
                .price(200D)
                .productId(1L)
                .quantity(BigDecimal.valueOf(1))
                .quantityType(QuantityType.PACK)
                .build();
        OrderDetail orderDetail = orderDetailMapper.toOrderDetailFromCreateRequest(createOrderDetailRequest);

        assertEquals(createOrderDetailRequest.getPrice(), orderDetail.getPrice());
        assertEquals(createOrderDetailRequest.getProductId(), orderDetail.getProduct().getId());
        assertEquals(createOrderDetailRequest.getQuantity(), orderDetail.getQuantity());
        assertEquals(createOrderDetailRequest.getQuantityType(), orderDetail.getQuantityType());
    }

    @Test
    public void shouldMapUpdateOrderDetailToOrderDetailResponse() {
        UpdateOrderDetailRequest updateOrderDetailRequest = UpdateOrderDetailRequest.builder()
                .id(1L)
                .price(200D)
                .productId(1L)
                .quantity(BigDecimal.valueOf(1))
                .quantityType(QuantityType.PACK)
                .build();
        OrderDetail orderDetail = orderDetailMapper.toOrderDetailFromUpdateRequest(updateOrderDetailRequest);
        assertEquals(updateOrderDetailRequest.getPrice(), orderDetail.getPrice());
        assertEquals(updateOrderDetailRequest.getProductId(), orderDetail.getProduct().getId());
        assertEquals(updateOrderDetailRequest.getQuantity(), orderDetail.getQuantity());
        assertEquals(updateOrderDetailRequest.getQuantityType(), orderDetail.getQuantityType());
        assertEquals(updateOrderDetailRequest.getId(), orderDetail.getId());
    }

}
