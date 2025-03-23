package com.oekrem.SpringMVCBackEnd.dto.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Category Response Model")
public record CategoryResponse (
        @Schema(name = "Category Id")
        Long id,
        @Schema(name = "Category Name")
        String name,
        @Schema(name = "Category Description")
        String description
){

}
