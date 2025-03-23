package com.oekrem.SpringMVCBackEnd.services;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.ProductResponse;
import com.oekrem.SpringMVCBackEnd.models.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<ProductResponse> findAll(int page, int size, Long categoryId);
    ProductResponse addProduct(CreateProductRequest product);
    ProductResponse updateProduct(Long id, UpdateProductRequest product);
    ProductResponse patchProduct(Long id, PatchProductRequest product);
    void deleteProduct(Long id);
    ProductResponse getProductById(Long id);

    Product validateProduct(Long id);
}
