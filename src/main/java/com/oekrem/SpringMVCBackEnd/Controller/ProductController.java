package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Dto.Response.ProductResponse;
import com.oekrem.SpringMVCBackEnd.Models.Category;
import com.oekrem.SpringMVCBackEnd.Models.Product;
import com.oekrem.SpringMVCBackEnd.Services.CategoryService;
import com.oekrem.SpringMVCBackEnd.Services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    @Qualifier("defaultModelMapper")
    private ModelMapper modelMapper;

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<ProductResponse> getProducts(){
        List<Product> products = productService.findAll();
        List<ProductResponse> productResponses = products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
        return products.stream().map(p -> modelMapper.map(p, ProductResponse.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
        productResponse.setCategory_id(product.getCategory().getId());
        return productResponse;
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        if(product.getCategory().getId() != null){
            Category category = categoryService.getCategoryById(product.getCategory().getId());
            product.setCategory(category);
        }
        productService.updateProduct(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
