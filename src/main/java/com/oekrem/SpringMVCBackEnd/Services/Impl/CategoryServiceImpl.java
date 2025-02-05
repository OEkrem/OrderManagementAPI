package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.CategoryRepository;
import com.oekrem.SpringMVCBackEnd.Models.Category;
import com.oekrem.SpringMVCBackEnd.Services.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {this.categoryRepository = categoryRepository;}

    @Override
    @Transactional
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        categoryRepository.addCategory(category);
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        categoryRepository.updateCategory(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        if(getCategoryById(id) != null){
            categoryRepository.deleteCategory(id);
        }
    }

    @Override
    @Transactional
    public Category getCategoryById(Long id) {
        return categoryRepository.getCategoryById(id);
    }
}
