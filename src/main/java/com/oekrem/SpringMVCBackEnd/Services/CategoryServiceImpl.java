package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.DataAccess.CategoryRepository;
import com.oekrem.SpringMVCBackEnd.Models.Category;
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
        if(category.getId() != null && getCategoryById(category.getId().intValue()) == null){
            categoryRepository.addCategory(category);
        }
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        if(getCategoryById(category.getId().intValue()) != null){
            categoryRepository.updateCategory(category);
        }
    }

    @Override
    @Transactional
    public void deleteCategory(Category category) {
        if(getCategoryById(category.getId().intValue()) != null){
            categoryRepository.deleteCategory(category);
        }
    }

    @Override
    @Transactional
    public Category getCategoryById(int id) {
        return categoryRepository.getCategoryById(id);
    }
}
