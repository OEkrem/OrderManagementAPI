package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Response.OrderDetailResponse;
import com.oekrem.SpringMVCBackEnd.dto.common.PageResponse;
import com.oekrem.SpringMVCBackEnd.services.OrderDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orderdetails")
@RequiredArgsConstructor
@Tag(name = "OrderDetail Controller", description = "Manages OrderDetail Operations")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Operation(summary = "Get All Order Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PageResponse.class),
                            array = @ArraySchema(schema = @Schema(implementation = OrderDetailResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing parameters)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins and Owners can access")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#orderId, T(com.oekrem.SpringMVCBackEnd.security.EntityType).ORDER ,authentication.name)")
    public ResponseEntity<PageResponse<OrderDetailResponse>> getOrderDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long orderId
    ){
        return ResponseEntity.ok(orderDetailService.findAll(page, size, orderId));
    }

    @Operation(summary = "Get Order Detail By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetailResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid OrderDetail id format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins and Owners can access"),
            @ApiResponse(responseCode = "404", description = "Order Detail Not Found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#id, T(com.oekrem.SpringMVCBackEnd.security.EntityType).ORDER_DETAILS ,authentication.name)")
    public ResponseEntity<OrderDetailResponse> getOrderDetailById(@PathVariable Long id){
        return ResponseEntity.ok(orderDetailService.getOrderDetailById(id));
    }

    @Operation(summary = "Delete Order Detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order Detail deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid Order Detail id format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins and Owners can delete"),
            @ApiResponse(responseCode = "404", description = "Order Detail Not Found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#id, T(com.oekrem.SpringMVCBackEnd.security.EntityType).ORDER_DETAILS ,authentication.name)")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id){
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }

    /*@PostMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailResponse> addOrderDetail(@PathVariable Long orderId,@RequestBody CreateOrderDetailRequest createOrderDetailRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetailService.addOrderDetail(orderId, createOrderDetailRequest));
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailResponse> updateOrderDetail(@PathVariable Long orderId, @RequestBody UpdateOrderDetailRequest updateOrderDetailRequest){
        return ResponseEntity.ok(orderDetailService.updateOrderDetail(orderId, updateOrderDetailRequest));
    }

    @PatchMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailResponse> patchOrderDetail(@PathVariable Long orderId, @RequestBody PatchOrderDetailRequest patchOrderDetailRequest){
        return ResponseEntity.ok(orderDetailService.patchOrderDetail(orderId, patchOrderDetailRequest));
    }*/

}
