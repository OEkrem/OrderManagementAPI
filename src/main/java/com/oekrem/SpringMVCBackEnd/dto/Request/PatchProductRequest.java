package com.oekrem.SpringMVCBackEnd.dto.Request;

import lombok.Builder;

@Builder
public record PatchProductRequest(
        String name,
        Long categoryId,
        String description,
        Double price,
        String image
) {
}
