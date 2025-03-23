package com.oekrem.SpringMVCBackEnd.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateCategoryRequest (

        @NotBlank(message = "Name is required")
        @Schema(name = "name", example = "Category Name")
        String name,
        @NotBlank(message = "Description is required")
        @Size(min = 3, max = 3000, message = "Description must be between {min} - {max} characters")
        @Schema(name = "description", example = "Category description example")
        String description

){
}
