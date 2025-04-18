package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.PatchOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailsResponse;
import com.oekrem.SpringMVCBackEnd.dto.common.PageResponse;
import com.oekrem.SpringMVCBackEnd.models.Product;
import com.oekrem.SpringMVCBackEnd.repository.OrderDetailRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.OrderDetailMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateOrderDetailRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.OrderDetailExceptions.OrderDetailNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Order;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.services.OrderDetailService;
import com.oekrem.SpringMVCBackEnd.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailMapper orderDetailMapper;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;

    @Override
    @Transactional
    public PageResponse<OrderDetailResponse> findAll(int page, int size, Long orderId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderDetail> orderDetails;
        if(orderId != null)
            orderDetails = orderDetailRepository.findByOrderId(pageable, orderId);
        else
            orderDetails = orderDetailRepository.findAll(pageable);
        Page<OrderDetailResponse> responsesPage = orderDetails.map(orderDetailMapper::toResponse);
        return PageResponse.fromPage(responsesPage);
    }

    @Override
    @Transactional
    public OrderDetailsResponse addOrderDetails(Order order, List<CreateOrderDetailRequest> createOrderDetailRequest) {
        List<OrderDetailResponse> responses = new ArrayList<>();
        for(CreateOrderDetailRequest createOrderDetail : createOrderDetailRequest){
            responses.add(addOrderDetail(order, createOrderDetail));
        }
        return OrderDetailsResponse.builder()
                .orderDetails(responses)
                .build();
    }

    @Override
    @Transactional
    public OrderDetailResponse addOrderDetail(Order order, CreateOrderDetailRequest createOrderDetailRequest){
        OrderDetail mappedOrderDetail = orderDetailMapper.toOrderDetailFromCreateRequest(createOrderDetailRequest);
        Product product = productService.validateProduct(createOrderDetailRequest.productId());
        mappedOrderDetail.setOrder(order);
        mappedOrderDetail.setProduct(product);

        OrderDetail savedOrderDetail;
        OrderDetail exisingOrderDetail = validateOrderDetailByOrderAndProductId(order, createOrderDetailRequest.productId());

        if(exisingOrderDetail != null){
            exisingOrderDetail.setQuantity(exisingOrderDetail.getQuantity() + createOrderDetailRequest.quantity());
            savedOrderDetail = orderDetailRepository.addOrderDetail(exisingOrderDetail);
        }
        else
            savedOrderDetail = orderDetailRepository.addOrderDetail(mappedOrderDetail);

        System.out.println("Id: " + savedOrderDetail.getId() + "   Price: " + savedOrderDetail.getPrice());
        return orderDetailMapper.toResponse(savedOrderDetail);
    }

    @Override
    @Transactional
    public OrderDetailResponse updateOrderDetail(Long orderDetailId, UpdateOrderDetailRequest updateOrderDetailRequest) {
        OrderDetail validateOrderDetail = validateOrderDetail(orderDetailId);

        OrderDetail orderDetail = orderDetailMapper.toOrderDetailFromUpdateRequest(updateOrderDetailRequest);
        orderDetail.setOrder(validateOrderDetail.getOrder());
        OrderDetail updatedOrderDetail = orderDetailRepository.updateOrderDetail(orderDetail);
        return orderDetailMapper.toResponse(updatedOrderDetail);
    }

    @Override
    @Transactional
    public OrderDetailResponse patchOrderDetail(Long orderDetailId, PatchOrderDetailRequest patchOrderDetailRequest) {
        OrderDetail orderDetail = validateOrderDetail(orderDetailId);

        orderDetailMapper.patchOrderDetail(patchOrderDetailRequest, orderDetail);
        OrderDetail savedOrderDetail = orderDetailRepository.updateOrderDetail(orderDetail);
        return orderDetailMapper.toResponse(savedOrderDetail);
    }

    @Override
    @Transactional
    public void deleteOrderDetail(Long id) {
        validateOrderDetail(id);
        orderDetailRepository.deleteOrderDetail(id);
    }

    @Override
    @Transactional
    public OrderDetailResponse getOrderDetailById(Long id) {
        return orderDetailMapper.toResponse(validateOrderDetail(id));
    }

    @Override
    @Transactional
    public OrderDetail validateOrderDetail(Long id){
        return orderDetailRepository.getOrderDetailById(id)
                .orElseThrow(() -> new OrderDetailNotFoundException("OrderDetail not found with id: " + id));
    }

    public OrderDetail validateOrderDetailByOrderAndProductId(Order order, Long productId){
        return order.getOrderDetails().stream().filter( p-> Objects.equals(p.getProduct().getId(), productId)).findFirst().orElse(null);
    }
}
