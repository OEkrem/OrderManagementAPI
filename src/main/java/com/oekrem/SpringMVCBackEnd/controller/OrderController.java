package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.*;
import com.oekrem.SpringMVCBackEnd.dto.Response.*;
import com.oekrem.SpringMVCBackEnd.dto.common.PageResponse;
import com.oekrem.SpringMVCBackEnd.models.enums.OrderStatus;
import com.oekrem.SpringMVCBackEnd.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "Manages order operations")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PageResponse.class),
                            array = @ArraySchema(schema = @Schema(implementation = OrderAllResponse.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing order parameters)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication required (JWT token)"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only admins and owners can get orders")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#userId, T(com.oekrem.SpringMVCBackEnd.security.EntityType).USER ,authentication.name)")
    public ResponseEntity<PageResponse<OrderAllResponse>> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) OrderStatus orderStatus
            ){
        return ResponseEntity.ok(orderService.findAllOrders(page, size, userId, orderStatus));
    }

    @Operation(summary = "Get order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication required (JWT Token)"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins and order owners can get order"),
            @ApiResponse(responseCode = "404", description = "Order Not Found")
    })
    @GetMapping("/{orderId}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#orderId, T(com.oekrem.SpringMVCBackEnd.security.EntityType).ORDER ,authentication.name)")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @Operation(summary = "Add Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing order details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication required (JWT Token)"),
            @ApiResponse(responseCode = "409", description = "Conflict, Order could not be created")
    })
    @PostMapping
    public ResponseEntity<OrderResponse> addOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(createOrderRequest));
    }

    @Operation(summary = "Add Payment To Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful to create payment for order",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing payment details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "404", description = "Order Not Found"),
            @ApiResponse(responseCode = "409", description = "Conflict, Payment could not be created")
    })
    @PatchMapping("/{orderId}/payment")
    public ResponseEntity<PaymentResponse> addPayment(@PathVariable Long orderId, @RequestBody CreatePaymentRequest createPaymentRequest){
        return ResponseEntity.ok(orderService.savePayment(orderId, createPaymentRequest));
    }

    @Operation(summary = "Add OrderDetail To Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful to create OrderDetail for order",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetailResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing OrderDetail information)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "404", description = "Order Not Found"),
            @ApiResponse(responseCode = "409", description = "Conflict, OrderDetail could not be created")
    })
    @PatchMapping("/{orderId}/orderdetail")
    public ResponseEntity<OrderDetailResponse> addOrderDetail(@PathVariable Long orderId, @RequestBody CreateOrderDetailRequest createOrderDetailRequests) {
        return ResponseEntity.ok(orderService.saveOrderDetail(orderId, createOrderDetailRequests));
    }

    @Operation(summary = "Add OrderDetails To Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful to create order details for order",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetailsResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing OrderDetails information)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "404", description = "Order Not Found"),
            @ApiResponse(responseCode = "409", description = "Conflict, OrderDetails could not be created")
    })
    @PatchMapping("/{orderId}/orderdetails")
    public ResponseEntity<OrderDetailsResponse> addOrderDetails(@PathVariable Long orderId, @RequestBody List<CreateOrderDetailRequest> createOrderDetailRequests) {
        return ResponseEntity.ok(orderService.saveOrderDetails(orderId, createOrderDetailRequests));
    }

    @Operation(summary = "Confirm Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid Order id format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "404", description = "Order Not Found")
    })
    @PatchMapping("/{orderId}/confirm")
    public ResponseEntity<OrderResponse> confirmOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.confirmOrder(orderId));
    }

    @Operation(summary = "Delete Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid Order id format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only admins and owners can delete an order"),
            @ApiResponse(responseCode = "404", description = "Order Not Found"),
    })
    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#orderId, T(com.oekrem.SpringMVCBackEnd.security.EntityType).ORDER ,authentication.name)")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

}
