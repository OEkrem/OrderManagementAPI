package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.repository.CategoryRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.CategoryMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.CategoryResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.CategoryExceptions.CategoryNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Category;
import com.oekrem.SpringMVCBackEnd.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryResponse addCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = categoryMapper.toCategoryFromCreateRequest(createCategoryRequest);
        Category addedCategory = categoryRepository.addCategory(category);
        return categoryMapper.toResponse(addedCategory);
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest) {
        validateCategory(id);
        Category category = categoryMapper.toCategoryFromUpdateRequest(updateCategoryRequest);
        category.setId(id);
        Category updatedCategory = categoryRepository.updateCategory(category);
        return categoryMapper.toResponse(updatedCategory);
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
        return categoryMapper.toResponse(category);
    }

    @Override
    public Category validateCategory(Long id) {
        return categoryRepository.getCategoryById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
    }
}
