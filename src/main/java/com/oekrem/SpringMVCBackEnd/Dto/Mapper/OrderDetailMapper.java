package com.oekrem.SpringMVCBackEnd.Dto.Mapper;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.Models.Order;
import com.oekrem.SpringMVCBackEnd.Models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.Models.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper {

    private Product product = new Product();
    private Order order = new Order();

    public OrderDetailResponse toResponse(OrderDetail orderDetail){
        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        orderDetailResponse.setId(orderDetail.getId());
        orderDetailResponse.setProductId(orderDetail.getProduct().getId());
        orderDetailResponse.setQuantityType(orderDetail.getQuantityType());
        orderDetailResponse.setQuantity(orderDetail.getQuantity());
        orderDetailResponse.setPrice(orderDetail.getPrice());
        orderDetailResponse.setOrderId(orderDetail.getOrder().getId());
        return orderDetailResponse;
    }

    public OrderDetail toOrderDetailFromOrderDetailResponse(OrderDetailResponse orderDetailResponse){
        order.setId(orderDetailResponse.getOrderId());
        product.setId(orderDetailResponse.getProductId());
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailResponse.getId());
        orderDetail.setQuantityType(orderDetailResponse.getQuantityType());
        orderDetail.setQuantity(orderDetailResponse.getQuantity());
        orderDetail.setPrice(orderDetailResponse.getPrice());
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        return orderDetail;
    }

    public CreateOrderDetailRequest toCreateOrderDetailRequest(OrderDetail orderDetail){
        CreateOrderDetailRequest createOrderDetailRequest = new CreateOrderDetailRequest();
        createOrderDetailRequest.setProductId(orderDetail.getProduct().getId());
        createOrderDetailRequest.setQuantityType(orderDetail.getQuantityType());
        createOrderDetailRequest.setQuantity(orderDetail.getQuantity());
        createOrderDetailRequest.setPrice(orderDetail.getPrice());
        return createOrderDetailRequest;
    }

    public OrderDetail toOrderDetailFromCreateOrderDetailRequest(CreateOrderDetailRequest createOrderDetailRequest){
        product.setId(createOrderDetailRequest.getProductId());
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantityType(createOrderDetailRequest.getQuantityType());
        orderDetail.setQuantity(createOrderDetailRequest.getQuantity());
        orderDetail.setPrice(createOrderDetailRequest.getPrice());
        orderDetail.setProduct(product);
        return orderDetail;
    }

    public UpdateOrderDetailRequest toUpdateOrderDetailRequest(OrderDetail orderDetail){
        UpdateOrderDetailRequest updateOrderDetailRequest = new UpdateOrderDetailRequest();
        updateOrderDetailRequest.setId(orderDetail.getId());
        updateOrderDetailRequest.setProductId(orderDetail.getProduct().getId());
        updateOrderDetailRequest.setQuantityType(orderDetail.getQuantityType());
        updateOrderDetailRequest.setQuantity(orderDetail.getQuantity());
        updateOrderDetailRequest.setPrice(orderDetail.getPrice());
        return updateOrderDetailRequest;
    }

    public OrderDetail toOrderDetailFromUpdateOrderDetailRequest(UpdateOrderDetailRequest updateOrderDetailRequest){
        product.setId(updateOrderDetailRequest.getProductId());
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(updateOrderDetailRequest.getId());
        orderDetail.setProduct(product);
        orderDetail.setQuantityType(updateOrderDetailRequest.getQuantityType());
        orderDetail.setQuantity(updateOrderDetailRequest.getQuantity());
        orderDetail.setPrice(updateOrderDetailRequest.getPrice());
        return orderDetail;
    }
}
