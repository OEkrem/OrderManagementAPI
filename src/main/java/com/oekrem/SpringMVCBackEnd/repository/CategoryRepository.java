package com.oekrem.SpringMVCBackEnd.repository;

import com.oekrem.SpringMVCBackEnd.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> findAll();
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
    Optional<Category> getCategoryById(Long id);
}
