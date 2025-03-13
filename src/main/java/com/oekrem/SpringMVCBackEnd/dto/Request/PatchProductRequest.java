package com.oekrem.SpringMVCBackEnd.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Patch Product Request Model")
public record PatchProductRequest(

        @Schema(name = "name", example = "Product Name")
        String name,
        @Schema(name = "categoryId", example = "1")
        Long categoryId,
        @Schema(name = "description", example = "Product description example")
        String description,
        @Schema(name = "price", example = "299.0")
        Double price,
        @Schema(name = "image", example = "/static/img/product/example.jpeg")
        String image

) {
}
