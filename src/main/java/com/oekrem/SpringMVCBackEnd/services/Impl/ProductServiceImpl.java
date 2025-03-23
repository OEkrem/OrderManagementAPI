package com.oekrem.SpringMVCBackEnd.services.Impl;

import com.oekrem.SpringMVCBackEnd.dto.Request.PatchProductRequest;
import com.oekrem.SpringMVCBackEnd.repository.ProductRepository;
import com.oekrem.SpringMVCBackEnd.dto.Mapper.ProductMapper;
import com.oekrem.SpringMVCBackEnd.dto.Request.CreateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.ProductResponse;
import com.oekrem.SpringMVCBackEnd.exceptions.ProductExceptions.ProductNotFoundException;
import com.oekrem.SpringMVCBackEnd.models.Product;
import com.oekrem.SpringMVCBackEnd.services.ProductService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public Page<ProductResponse> findAll(int page, int size, Long categoryId) {
        Pageable pageable = PageRequest.of(page, size);
        if(categoryId != null)
            return productRepository.findByCategoryId(pageable, categoryId).map(productMapper::toResponse);
        else
            return productRepository.findAll(pageable).map(productMapper::toResponse);
    }

    @Override
    @Transactional
    public ProductResponse addProduct(CreateProductRequest product) {
        Product savedProduct = productRepository.addProduct(productMapper.toProductFromCreateRequest(product));
        return productMapper.toResponse(savedProduct);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, UpdateProductRequest product) {
        validateProduct(id);
        Product productToUpdate = productMapper.toProductFromUpdateRequest(product);
        productToUpdate.setId(id);

        Product updatedProduct = productRepository.updateProduct(productToUpdate);
        return productMapper.toResponse(updatedProduct);
    }

    @Override
    @Transactional
    public ProductResponse patchProduct(Long id, PatchProductRequest patchProductRequest) {
        Product validateProduct = validateProduct(id);

        productMapper.patchProduct(patchProductRequest, validateProduct);
        Product savedProduct = productRepository.updateProduct(validateProduct);
        return productMapper.toResponse(savedProduct);
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
    public Product validateProduct(Long id){
        return productRepository.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

}
