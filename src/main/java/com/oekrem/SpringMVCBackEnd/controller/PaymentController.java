package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.models.enums.PaymentStatus;
import com.oekrem.SpringMVCBackEnd.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Tag(name = "Payment Controller", description = "Manages Payment Operations")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Get All Payments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class),
                            array = @ArraySchema(schema = @Schema(implementation = PaymentResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing parameters)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins can access")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PaymentResponse>> getPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) PaymentStatus paymentStatus
            ){
        return ResponseEntity.ok(paymentService.findAll(page, size, paymentStatus));
    }

    @Operation(summary = "Get Payment By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid Payment id format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins and Owners can access"),
            @ApiResponse(responseCode = "404", description = "Payment Not Found")
    })
    @GetMapping("/{paymentId}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#paymentId, T(com.oekrem.SpringMVCBackEnd.security.EntityType).PAYMENT ,authentication.name)")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long paymentId){
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

    @Operation(summary = "Delete Payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Payment deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid Payment id format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins and Owners can delete"),
            @ApiResponse(responseCode = "404", description = "Payment Not Found"),
    })
    @DeleteMapping("/{paymentId}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(#paymentId, T(com.oekrem.SpringMVCBackEnd.security.EntityType).PAYMENT ,authentication.name)")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId){
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }

    /*@PutMapping("{paymentId}")
    public ResponseEntity<PaymentResponse> updatePayment(@PathVariable Long paymentId, @RequestBody UpdatePaymentRequest updatePaymentRequest){
        return ResponseEntity.ok(paymentService.updatePayment(paymentId, updatePaymentRequest));
    }

    @PatchMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> patchPayment(@PathVariable Long paymentId, @RequestBody PatchPaymentRequest patchPaymentRequest){
        return ResponseEntity.ok(paymentService.patchPayment(paymentId, patchPaymentRequest));
    }*/

}
