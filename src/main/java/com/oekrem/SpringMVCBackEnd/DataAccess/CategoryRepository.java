package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Long id);
    Category getCategoryById(Long id);
}
