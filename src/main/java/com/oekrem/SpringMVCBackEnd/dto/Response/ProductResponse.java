package com.oekrem.SpringMVCBackEnd.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Product Response Model")
public record ProductResponse (
        @Schema(name = "Product id")
        Long id,
        @Schema(name = "Product name")
        String name,
        @Schema(name = "Category id")
        Long category_id,
        @Schema(name = "Description")
        String description,
        @Schema(name = "Price of product")
        Double price,
        @Schema(name = "İmage url of product")
        String image
){

}
