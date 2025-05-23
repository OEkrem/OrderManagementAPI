package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.CategoryResponse;
import com.oekrem.SpringMVCBackEnd.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CategoryMapperUnitTest {

    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setUp() {
        categoryMapper = Mappers.getMapper(CategoryMapper.class);
    }

    @Test
    public void shouldMapCategoryToCategoryResponse() {
        Category category = Category.builder()
                .id(1L)
                .name("Test")
                .description("Test")
                .products(null)
                .build();
        CategoryResponse response = categoryMapper.toResponse(category);

        assertEquals(category.getId(), response.id());
        assertEquals(category.getName(), response.name());
        assertEquals(category.getDescription(), response.description());
    }

    @Test
    public void shouldMapCreateRequestToCategory(){
        CreateCategoryRequest createCategoryRequest = CreateCategoryRequest.builder()
                .name("Test")
                .description("Test")
                .build();
        Category category = categoryMapper.toCategoryFromCreateRequest(createCategoryRequest);

        assertNull(category.getId());
        assertEquals(createCategoryRequest.name(), category.getName());
        assertEquals(createCategoryRequest.description(), category.getDescription());
    }

    @Test
    public void shouldMapUpdateRequestToCategory(){
        UpdateCategoryRequest updateCategoryRequest = UpdateCategoryRequest.builder()
                .name("Test")
                .description("Test")
                .build();
        Category category = categoryMapper.toCategoryFromUpdateRequest(updateCategoryRequest);
        assertNull(category.getId());
        assertEquals(updateCategoryRequest.name(), category.getName());
        assertEquals(updateCategoryRequest.description(), category.getDescription());
    }

    @Test
    public void shouldMapPatchRequestToCategory(){
        PatchCategoryRequest patchCategoryRequest = PatchCategoryRequest.builder()
                .name("Test")
                .description("Test")
                .build();
        Category category = Category.builder().id(1L).build();

        categoryMapper.patchCategory(patchCategoryRequest, category);

        assertEquals(category.getName(), patchCategoryRequest.name());
        assertEquals(category.getDescription(), patchCategoryRequest.description());
    }

}
