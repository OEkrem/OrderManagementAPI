package com.oekrem.SpringMVCBackEnd.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

// NoArgsConstructor, cache'e kaydedildikten sonra deserialize edebilmesi için gerekiyor.

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Page Response Model")
public class PageResponse<T> implements Serializable {

    @Schema(name = "content", description = "returns generic type list of dataset")
    private List<T> content;
    @Schema(name = "pageNumber")
    private int pageNumber;
    @Schema(name = "pageSize")
    private int pageSize;
    @Schema(name = "totalElements")
    private long totalElements;
    @Schema(name = "totalPages", description = "returns number of pages")
    private int totalPages;
    @Schema(name = "last", description = "isLastPage? returns boolean")
    private boolean last;

    public static <T> PageResponse<T> fromPage(org.springframework.data.domain.Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
