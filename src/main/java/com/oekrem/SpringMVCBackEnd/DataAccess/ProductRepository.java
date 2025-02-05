package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
    Product getProductById(Long id);
}
