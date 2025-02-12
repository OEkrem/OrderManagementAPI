package com.oekrem.SpringMVCBackEnd.dto.Mapper.CustomMapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.ProductResponse;
import com.oekrem.SpringMVCBackEnd.models.Category;
import com.oekrem.SpringMVCBackEnd.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setCategory_id(product.getCategory().getId());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setImage(product.getImage());
        return productResponse;
    }

    public Product toProductFromResponse(ProductResponse productResponse) {
        Category category = new Category();
        category.setId(productResponse.getCategory_id());
        Product product = new Product();
        product.setId(productResponse.getId());
        product.setName(productResponse.getName());
        product.setCategory(category);
        product.setDescription(productResponse.getDescription());
        product.setPrice(productResponse.getPrice());
        product.setImage(productResponse.getImage());
        return product;
    }

    //create and update requests
    public Product toProductFromCreateProductRequest(CreateProductRequest createProductRequest) {
        Category category = new Category();
        category.setId(createProductRequest.getCategoryId());

        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setCategory(category);
        product.setDescription(createProductRequest.getDescription());
        product.setPrice(createProductRequest.getPrice());
        product.setImage(createProductRequest.getImage());
        return product;
    }

    public CreateProductRequest toCreateProductRequest(Product product) {
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setName(product.getName());
        createProductRequest.setCategoryId(product.getCategory().getId());
        createProductRequest.setDescription(product.getDescription());
        createProductRequest.setPrice(product.getPrice());
        createProductRequest.setImage(product.getImage());
        return createProductRequest;
    }

    public Product toProductFromUpdateProductRequest(UpdateProductRequest updateProductRequest) {
        Category category = new Category();
        category.setId(updateProductRequest.getCategoryId());
        Product product = new Product();
        product.setName(updateProductRequest.getName());
        product.setCategory(category);
        product.setDescription(updateProductRequest.getDescription());
        product.setPrice(updateProductRequest.getPrice());
        product.setImage(updateProductRequest.getImage());
        return product;
    }
}
