package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.ProductResponse;
import com.oekrem.SpringMVCBackEnd.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<CreateProductRequest> addProduct(@RequestBody CreateProductRequest createProductRequest){
        productService.addProduct(createProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createProductRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateProductRequest> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest updateProductRequest){
        productService.updateProduct(id, updateProductRequest);
        return ResponseEntity.ok(updateProductRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories/{id}")
    public List<ProductResponse> getProductsByCategory(@PathVariable Long id){
        return productService.getProductsByCategoryId(id);
    }

}
