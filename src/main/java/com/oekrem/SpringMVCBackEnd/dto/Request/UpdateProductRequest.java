package com.oekrem.SpringMVCBackEnd.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "Update Product Request Model")
public record UpdateProductRequest (

        @NotBlank(message = "Name is required")
                @Schema(name = "name", example = "Product Name")
        String name,

        @NotNull(message = "Category is required")
                @Schema(name = "categoryId", example = "1")
        Long categoryId,

        //@NotNull(message = "Description is required")
        @Size(min = 3, max = 3000, message = "Description must be between {min} - {max} characters")
                @Schema(name = "description", example = "Product description example")
        String description,

        @NotNull(message = "Price is required")
                @Schema(name = "price", example = "299.0")
        Double price,

        //@NotNull(message = "Image is required")
        @Schema(name = "image", example = "/static/img/product/example.jpeg")
        String image
){
}
