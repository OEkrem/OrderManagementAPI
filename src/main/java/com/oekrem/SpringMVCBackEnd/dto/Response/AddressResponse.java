package com.oekrem.SpringMVCBackEnd.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Builder;

@Builder
@Schema(name = "Address Response Model")
public record AddressResponse (
        @Schema(name = "Address id")
        Long id,
        @Schema(name = "Address Name")
        String name,
        @Schema(name = "Door number", example = "1")
        Integer doorNumber,
        @Schema(name = "Floor", example = "2")
        Integer floor,
        @Schema(name = "Building Number")
        String buildingNumber,
        @Schema(name = "Street")
        String street,
        @Schema(name = "City")
        String city,
        @Schema(name = "Country")
        String country,
        @Schema(name = "User id")
        Long userId
){

}
