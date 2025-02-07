package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Product addProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    Optional<Product> getProductById(Long id);

    List<Product> getProductsByCategoryId(Long categoryId);
}
