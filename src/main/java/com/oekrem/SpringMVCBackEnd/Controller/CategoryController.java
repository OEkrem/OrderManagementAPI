package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Models.Category;
import com.oekrem.SpringMVCBackEnd.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {this.categoryService = categoryService;}

    @GetMapping
    public List<Category> getCategories(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateUser(@PathVariable Long id, @RequestBody Category category){
        category.setId(id);
        categoryService.updateCategory(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
