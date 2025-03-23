package com.oekrem.SpringMVCBackEnd.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Patch Address Request Model")
public record PatchAddressRequest (

        @Schema(name = "name", example = "Home")
        String name,
        @Schema(name = "doorNumber", example = "1")
        Integer doorNumber,
        @Schema(name = "floor", example = "1")
        Integer floor,
        @Schema(name = "buildingNumber", example = "5/5")
        String buildingNumber,
        @Schema(name = "street", example = "St. example")
        String street,
        @Schema(name = "city", example = "City Name")
        String city,
        @Schema(name = "country", example = "Country Name")
        String country
){
}
