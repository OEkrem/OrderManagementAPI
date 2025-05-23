package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.CategoryResponse;
import com.oekrem.SpringMVCBackEnd.dto.common.PageResponse;
import com.oekrem.SpringMVCBackEnd.models.Category;

public interface CategoryService {
    PageResponse<CategoryResponse> findAll(int page, int size);
    CategoryResponse addCategory(CreateCategoryRequest createCategoryRequest);
    CategoryResponse updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest);
    CategoryResponse patchCategory(Long id, PatchCategoryRequest patchCategoryRequest);
    void deleteCategory(Long id);
    CategoryResponse getCategoryById(Long id);

    Category validateCategory(Long id);
}
