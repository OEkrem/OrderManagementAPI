package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.CategoryResponse;
import com.oekrem.SpringMVCBackEnd.Models.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
    CreateCategoryRequest addCategory(CreateCategoryRequest createCategoryRequest);
    UpdateCategoryRequest updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest);
    void deleteCategory(Long id);
    CategoryResponse getCategoryById(Long id);

    Category validateCategory(Long id);
}
