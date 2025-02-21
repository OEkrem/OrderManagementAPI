package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.CategoryResponse;
import com.oekrem.SpringMVCBackEnd.models.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
    CategoryResponse addCategory(CreateCategoryRequest createCategoryRequest);
    CategoryResponse updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest);
    void deleteCategory(Long id);
    CategoryResponse getCategoryById(Long id);

    Category validateCategory(Long id);
}
