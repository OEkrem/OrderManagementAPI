package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.ProductRepository;
import com.oekrem.SpringMVCBackEnd.Models.Category;
import com.oekrem.SpringMVCBackEnd.Models.Product;
import com.oekrem.SpringMVCBackEnd.Services.CategoryService;
import com.oekrem.SpringMVCBackEnd.Services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        if(product.getCategory() != null && product.getCategory().getId() != null){
            Optional<Category> existCategory = Optional.ofNullable(categoryService.getCategoryById(product.getCategory().getId()));
            if(existCategory.isPresent())
                product.setCategory(existCategory.get());

            System.out.println("Product setCategory is completed succesfuly");
        }
        productRepository.addProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }

    @Override
    @Transactional
    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }
}
