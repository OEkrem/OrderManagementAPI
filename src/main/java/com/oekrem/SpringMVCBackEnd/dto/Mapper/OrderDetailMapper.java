package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDetailMapper {

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "product.id", target = "productId")
    OrderDetailResponse toResponse(OrderDetail orderDetail);

    @Mapping(source = "productId", target = "product.id")
    OrderDetail toOrderDetailFromCreateRequest(CreateOrderDetailRequest orderDetailRequest);

    @Mapping(source = "productId", target = "product.id")
    OrderDetail toOrderDetailFromUpdateRequest(UpdateOrderDetailRequest orderDetailRequest);

    @Mapping(target = "id", ignore = true)
    void patchOrderDetail(UpdateOrderDetailRequest updateOrderDetailRequest, @MappingTarget OrderDetail orderDetail);

    List<OrderDetailResponse> toResponseList(List<OrderDetail> orderDetails);

}
