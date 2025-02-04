package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Category;
import com.oekrem.SpringMVCBackEnd.Models.User;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Category category);
    Category getCategoryById(int id);
}
