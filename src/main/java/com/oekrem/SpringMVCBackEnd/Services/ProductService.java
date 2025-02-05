package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.Models.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
}
