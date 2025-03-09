package com.oekrem.SpringMVCBackEnd.dto.Request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductRequest {

    private String name;
    private Long categoryId;
    private String description;
    private Double price;
    private String image;

}
