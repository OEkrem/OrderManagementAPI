package com.oekrem.SpringMVCBackEnd.DataAccess;

import com.oekrem.SpringMVCBackEnd.Models.Product;
import com.oekrem.SpringMVCBackEnd.Models.User;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    Product getProductById(int id);
}
