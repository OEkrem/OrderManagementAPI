package com.oekrem.SpringMVCBackEnd.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {

    private Long id;
    private String name;
    private Integer doorNumber;
    private Integer floor;
    private String buildingNumber;
    private String street;
    private String city;
    private String country;
    private Long userId;

}
