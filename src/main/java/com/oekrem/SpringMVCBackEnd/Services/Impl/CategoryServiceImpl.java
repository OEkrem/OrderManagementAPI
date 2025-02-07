package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.CategoryRepository;
import com.oekrem.SpringMVCBackEnd.Dto.Mapper.CategoryMapper;
import com.oekrem.SpringMVCBackEnd.Dto.Response.CategoryResponse;
import com.oekrem.SpringMVCBackEnd.Exceptions.CategoryExceptions.CategoryNotFoundException;
import com.oekrem.SpringMVCBackEnd.Models.Category;
import com.oekrem.SpringMVCBackEnd.Services.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        categoryRepository.addCategory(category);
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        validateCategory(category.getId());
        categoryRepository.updateCategory(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        validateCategory(id);
        categoryRepository.deleteCategory(id);
    }

    @Override
    @Transactional
    public CategoryResponse getCategoryById(Long id) {
        Category category = validateCategory(id);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public Category validateCategory(Long id) {
        return categoryRepository.getCategoryById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
    }
}
