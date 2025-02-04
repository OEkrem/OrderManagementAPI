package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Models.Category;
import com.oekrem.SpringMVCBackEnd.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {this.categoryService = categoryService;}

    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/category/add")
    public void addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
    }

    @PostMapping("/category/update")
    public void updateUser(@RequestBody Category category){
        categoryService.updateCategory(category);
    }

    @PostMapping("/category/delete")
    public void deleteUser(@RequestBody Category category){
        categoryService.deleteCategory(category);
    }
}
