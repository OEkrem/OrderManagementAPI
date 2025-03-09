package com.oekrem.SpringMVCBackEnd.dto.Request;

import lombok.Builder;

@Builder
public record PatchCategoryRequest (
        String name,
        String description
){
}
