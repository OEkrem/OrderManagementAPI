package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.repository.ProductRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.CustomMapper.ProductMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.ProductResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.ProductExceptions.ProductNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Product;
import com.oekrem.SpringMVCBackEnd.services.CategoryService;
import com.oekrem.SpringMVCBackEnd.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

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
