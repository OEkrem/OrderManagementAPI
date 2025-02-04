package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Category category);
    Category getCategoryById(int id);
}
