package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.ProductResponse;
import com.oekrem.SpringMVCBackEnd.models.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();
    ProductResponse addProduct(CreateProductRequest product);
    ProductResponse updateProduct(Long id, UpdateProductRequest product);
    void deleteProduct(Long id);
    ProductResponse getProductById(Long id);

    List<ProductResponse> getProductsByCategoryId(Long categoryId);

    Product validateProduct(Long id);
}
