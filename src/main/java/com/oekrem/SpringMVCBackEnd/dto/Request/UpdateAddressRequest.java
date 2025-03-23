package com.oekrem.SpringMVCBackEnd.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(name = "Update Address Request Model")
public record UpdateAddressRequest (

        @NotBlank(message = "Address name is required")
        @Schema(name = "name", example = "Home")
        String name,
        @NotNull(message = "Door Number is required")
        @Schema(name = "doorNumber", example = "1")
        Integer doorNumber,
        @NotNull(message = "Floor is required")
        @Schema(name = "floor", example = "1")
        Integer floor,
        @NotBlank(message = "Building Number is required")
        @Schema(name = "buildingNumber", example = "5/5")
        String buildingNumber,
        @NotBlank(message = "Street is required")
        @Schema(name = "street", example = "St. example")
        String street,
        @NotBlank(message = "City is required")
        @Schema(name = "city", example = "City Name")
        String city,
        @NotBlank(message = "Country is required")
        @Schema(name = "country", example = "Country Name")
        String country
){
}
