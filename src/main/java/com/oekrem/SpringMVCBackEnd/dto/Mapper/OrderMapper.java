package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderAllResponse;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderResponse;
import com.oekrem.SpringMVCBackEnd.models.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {OrderDetailMapper.class})
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderResponse toResponse(Order order);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderDetail", target = "orderDetailResponses")
    OrderAllResponse toResponseAll(Order order);

    Order toOrderFromCreateRequest(CreateOrderRequest createOrderRequest);
    Order toOrderFromUpdateRequest(UpdateOrderRequest updateOrderRequest);

    @Mapping(target = "id", ignore = true)
    void patchOrder(UpdateOrderRequest updateOrderRequest, @MappingTarget Order order);

}
