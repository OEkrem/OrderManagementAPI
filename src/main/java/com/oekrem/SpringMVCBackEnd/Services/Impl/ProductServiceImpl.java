package com.oekrem.SpringMVCBackEnd.Services.Impl;

import com.oekrem.SpringMVCBackEnd.DataAccess.ProductRepository;
import com.oekrem.SpringMVCBackEnd.Dto.Mapper.CustomMapper.ProductMapper;
import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateProductRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateProductRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.ProductResponse;
import com.oekrem.SpringMVCBackEnd.Exceptions.ProductExceptions.ProductNotFoundException;
import com.oekrem.SpringMVCBackEnd.Models.Product;
import com.oekrem.SpringMVCBackEnd.Services.CategoryService;
import com.oekrem.SpringMVCBackEnd.Services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CreateProductRequest addProduct(CreateProductRequest product) {
        System.out.println(product);
        productRepository.addProduct(productMapper.toProductFromCreateProductRequest(product));
        return product;
    }

    @Override
    @Transactional
    public UpdateProductRequest updateProduct(Long id, UpdateProductRequest product) {
        validateProduct(id);
        Product productToUpdate = productMapper.toProductFromUpdateProductRequest(product);
        productToUpdate.setId(id);
        productRepository.updateProduct(productToUpdate);
        return product;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        validateProduct(id);
        productRepository.deleteProduct(id);
    }

    @Override
    @Transactional
    public ProductResponse getProductById(Long id) {
        return productMapper.toResponse(validateProduct(id));
    }

    @Override
    @Transactional
    public List<ProductResponse> getProductsByCategoryId(Long categoryId) {
        categoryService.validateCategory(categoryId);
        return productRepository.getProductsByCategoryId(categoryId).stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Product validateProduct(Long id){
        return productRepository.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }
}
