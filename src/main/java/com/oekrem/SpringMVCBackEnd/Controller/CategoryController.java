package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.CategoryResponse;
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
    public List<CategoryResponse> getCategories(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<CreateCategoryRequest> addCategory(@RequestBody CreateCategoryRequest createCategoryRequest){
        categoryService.addCategory(createCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCategoryRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateCategoryRequest> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryRequest updateCategoryRequest){
        categoryService.updateCategory(id, updateCategoryRequest);
        return ResponseEntity.ok(updateCategoryRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
