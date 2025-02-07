package com.oekrem.SpringMVCBackEnd.Dto.Mapper;

import com.oekrem.SpringMVCBackEnd.Dto.Response.CategoryResponse;
import com.oekrem.SpringMVCBackEnd.Models.Category;
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

}
