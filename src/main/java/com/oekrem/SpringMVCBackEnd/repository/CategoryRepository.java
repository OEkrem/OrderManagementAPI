package com.oekrem.SpringMVCBackEnd.repository;

import com.oekrem.SpringMVCBackEnd.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryRepository {
    Page<Category> findAll(Pageable pageable);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
    Optional<Category> getCategoryById(Long id);
}
