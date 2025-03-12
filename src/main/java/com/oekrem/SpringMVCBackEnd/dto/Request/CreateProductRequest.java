package com.oekrem.SpringMVCBackEnd.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateProductRequest (
        @NotBlank(message = "Product name is required")
        @Size(min = 3, message = "Name must be include al least 3 characters")
        String name,

        @NotNull(message = "Category is required")
        Long categoryId,

        @NotBlank(message = "Description is required")
        @Size(min = 3, max = 3000, message = "Description must be between {min} - {max} characters")
        String description,

        @NotNull(message = "Price is required")
        Double price,

        String image
){

}
