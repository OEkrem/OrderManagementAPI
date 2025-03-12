package com.oekrem.SpringMVCBackEnd.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCategoryRequest {

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 3000, message = "Description must be between {min} - {max} characters")
    private String description;

}
