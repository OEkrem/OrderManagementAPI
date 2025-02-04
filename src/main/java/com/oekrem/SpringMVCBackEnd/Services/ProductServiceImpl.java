package com.oekrem.SpringMVCBackEnd.Services;

import com.oekrem.SpringMVCBackEnd.DataAccess.ProductRepository;
import com.oekrem.SpringMVCBackEnd.Models.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {this.productRepository = productRepository;}

    @Override
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {
        productRepository.deleteProduct(product);
    }

    @Override
    @Transactional
    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }
}
