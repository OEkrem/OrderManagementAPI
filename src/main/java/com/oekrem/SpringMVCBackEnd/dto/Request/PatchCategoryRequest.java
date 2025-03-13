package com.oekrem.SpringMVCBackEnd.dto.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "Patch Category Request Model")
public record PatchCategoryRequest (

        @Schema(name = "name", example = "Tablets")
        String name,

        @Size(min = 3, max = 3000, message = "Description must be between {min} - {max} characters")
        @Schema(name = "description", example = "Category description example")
        String description
){
}
