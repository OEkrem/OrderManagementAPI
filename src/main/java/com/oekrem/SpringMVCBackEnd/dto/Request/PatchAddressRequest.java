package com.oekrem.SpringMVCBackEnd.dto.Request;

import lombok.Builder;

@Builder
public record PatchAddressRequest (
        String name,
        Integer doorNumber,
        Integer floor,
        String buildingNumber,
        String street,
        String city,
        String country
){
}
