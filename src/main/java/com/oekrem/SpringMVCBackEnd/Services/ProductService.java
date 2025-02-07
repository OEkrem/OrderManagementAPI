package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Dto.Request.CreateProductRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Request.UpdateProductRequest;
import com.oekrem.SpringMVCBackEnd.Dto.Response.ProductResponse;
import com.oekrem.SpringMVCBackEnd.Models.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();
    CreateProductRequest addProduct(CreateProductRequest product);
    UpdateProductRequest updateProduct(Long id, UpdateProductRequest product);
    void deleteProduct(Long id);
    ProductResponse getProductById(Long id);

    List<ProductResponse> getProductsByCategoryId(Long categoryId);

    Product validateProduct(Long id);
}
