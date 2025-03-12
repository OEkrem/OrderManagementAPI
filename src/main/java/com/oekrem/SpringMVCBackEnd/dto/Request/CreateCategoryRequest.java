package com.oekrem.SpringMVCBackEnd.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateCategoryRequest (
        @NotBlank(message = "Category Name is required")
        String name,
        @Size(min = 3, max = 3000, message = "Description must be between {min} - {max} characters")
        String description
){

}
