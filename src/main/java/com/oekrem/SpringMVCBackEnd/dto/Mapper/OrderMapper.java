package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {OrderDetailMapper.class, PaymentMapper.class})
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "date",  target = "date")
    @Mapping(source = "id", target = "id")
    OrderResponse toResponse(Order order);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderDetails", target = "orderDetailResponses")
    @Mapping(source = "payment", target = "payment")
    OrderAllResponse toResponseAll(Order order);

    Order toOrderFromCreateRequest(CreateOrderRequest createOrderRequest);
    Order toOrderFromUpdateRequest(UpdateOrderRequest updateOrderRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "orderDetails", target = "orderDetails")
    @Mapping(source = "payment", target = "payment")
    void patchOrder(PatchOrderRequest patchOrderRequest, @MappingTarget Order order);

}
