package com.oekrem.SpringMVCBackEnd.dto.Mapper.CustomMapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.CategoryResponse;
import com.oekrem.SpringMVCBackEnd.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toCategoryFromResponse(CategoryResponse categoryResponse) {
        Category category = new Category();
        category.setId(categoryResponse.getId());
        category.setName(categoryResponse.getName());
        category.setDescription(categoryResponse.getDescription());
        return category;
    }

    public CategoryResponse toCategoryResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
        return categoryResponse;
    }

    public Category toCategoryFromCreateCategoryRequest(CreateCategoryRequest createCategoryRequest) {
        Category category = new Category();
        category.setName(createCategoryRequest.getName());
        category.setDescription(createCategoryRequest.getDescription());
        return category;
    }

    public Category toCategoryFromUpdateCategoryRequest(UpdateCategoryRequest updateCategoryRequest) {
        Category category = new Category();
        category.setName(updateCategoryRequest.getName());
        category.setDescription(updateCategoryRequest.getDescription());
        return category;
    }

}
