package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.CategoryResponse;
import com.oekrem.SpringMVCBackEnd.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

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
