package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.PatchCategoryRequest;
import com.oekrem.SpringMVCBackEnd.repository.CategoryRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.CategoryMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.CategoryResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.CategoryExceptions.CategoryNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Category;
import com.oekrem.SpringMVCBackEnd.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Page<CategoryResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.map(categoryMapper::toResponse);
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
    public CategoryResponse patchCategory(Long id, PatchCategoryRequest patchCategoryRequest) {
        Category category = validateCategory(id);
        categoryMapper.patchCategory(patchCategoryRequest, category);
        Category savedCategory = categoryRepository.updateCategory(category);
        return categoryMapper.toResponse(savedCategory);
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
