package com.oekrem.SpringMVCBackEnd.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "Create Product Request Model")
public record CreateProductRequest (

        @NotBlank(message = "Product name is required")
        @Size(min = 3, message = "Name must be include al least 3 characters")
        @Schema(name = "name", example = "Product Name")
        String name,

        @NotNull(message = "Category is required")
        @Schema(name = "categoryId", example = "1")
        Long categoryId,

        @NotBlank(message = "Description is required")
        @Size(min = 3, max = 3000, message = "Description must be between {min} - {max} characters")
        @Schema(name = "description", example = "Product's Description example")
        String description,

        @NotNull(message = "Price is required")
        @Schema(name = "price", example = "299.0")
        Double price,

        @Schema(name = "image", example = "/static/img/products/example.jpeg")
        String image
){

}
