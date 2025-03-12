package com.oekrem.SpringMVCBackEnd.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateAddressRequest (

        @NotBlank(message = "Address name is required")
        String name,
        @NotNull(message = "Door Number is required")
        Integer doorNumber,
        @NotNull(message = "Floor is required")
        Integer floor,
        @NotBlank(message = "Building Number is required")
        String buildingNumber,
        @NotBlank(message = "Street is required")
        String street,
        @NotBlank(message = "City is required")
        String city,
        @NotBlank(message = "Country is required")
        String country
){
}
