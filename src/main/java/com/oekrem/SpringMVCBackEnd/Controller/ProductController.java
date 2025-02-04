package com.oekrem.SpringMVCBackEnd.Controller;

import com.oekrem.SpringMVCBackEnd.Models.Category;
import com.oekrem.SpringMVCBackEnd.Models.Product;
import com.oekrem.SpringMVCBackEnd.Services.CategoryService;
import com.oekrem.SpringMVCBackEnd.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping("/product/add")
    public void addProduct(@RequestBody Product product){
        if(product.getCategory() != null){
            Category category = categoryService.getCategoryById(product.getCategory().getId().intValue());
            product.setCategory(category);
        }
        productService.addProduct(product);
    }

    @PostMapping("/product/update")
    public void updateProduct(@RequestBody Product product){
        if(product.getCategory() != null){
            Category category = categoryService.getCategoryById(product.getCategory().getId().intValue());
            product.setCategory(category);
        }
        productService.updateProduct(product);
    }

    @PostMapping("/product/delete")
    public void deleteProduct(@RequestBody Product product){
        productService.deleteProduct(product);
    }

}
