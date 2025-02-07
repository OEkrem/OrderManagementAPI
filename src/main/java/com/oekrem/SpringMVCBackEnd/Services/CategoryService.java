package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Dto.Response.CategoryResponse;
import com.oekrem.SpringMVCBackEnd.Models.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Long id);
    CategoryResponse getCategoryById(Long id);

    Category validateCategory(Long id);
}
