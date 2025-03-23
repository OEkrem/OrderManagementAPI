package com.oekrem.SpringMVCBackEnd.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "Create Category Request Model")
public record CreateCategoryRequest (

        @NotBlank(message = "Category Name is required")
        @Schema(name = "name", example = "Phones")
        String name,
        @Size(min = 3, max = 3000, message = "Description must be between {min} - {max} characters")
        @Schema(name = "description", example = "This is a description about phones")
        String description

){

}
