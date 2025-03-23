package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchPaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdatePaymentRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.PaymentResponse;
import com.oekrem.SpringMVCBackEnd.models.Payment;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentMapper {

    @Mapping(target = "orderId", source = "order.id")
    PaymentResponse toResponse(Payment payment);

    @Mapping(target = "order.id", source = "orderId")
    Payment toPaymentFromCreateRequest(CreatePaymentRequest createPaymentRequest);
    Payment toPaymentFromUpdateRequest(UpdatePaymentRequest updatePaymentRequest);

    @Mapping(target = "id", ignore = true)
    void patchPayment(PatchPaymentRequest patchPaymentRequest, @MappingTarget Payment payment);

}
