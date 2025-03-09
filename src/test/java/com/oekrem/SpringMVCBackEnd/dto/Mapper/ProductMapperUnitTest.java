package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.ProductResponse;
import com.oekrem.SpringMVCBackEnd.models.Category;
import com.oekrem.SpringMVCBackEnd.models.OrderDetail;
import com.oekrem.SpringMVCBackEnd.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductMapperUnitTest {

    private ProductMapper productMapper;

    @BeforeEach
    public void setUp() {
        productMapper = Mappers.getMapper(ProductMapper.class);
    }

    @Test
    public void shouldMapProductToResponse(){
        Product product = Product.builder()
                .id(1L)
                .name("Product Name")
                .description("Product Description")
                .price(BigDecimal.valueOf(100))
                .image("Product Image")
                .category(Category.builder().id(1L).build())
                .orderDetail(List.of(OrderDetail.builder().id(1L).build()))
                .build();
        ProductResponse response = productMapper.toResponse(product);

        assertEquals(product.getId(), response.getId());
        assertEquals(product.getName(), response.getName());
        assertEquals(product.getDescription(), response.getDescription());
        assertEquals(product.getPrice(), response.getPrice());
        assertEquals(product.getImage(), response.getImage());
        assertEquals(product.getCategory().getId(), response.getCategory_id());
    }

    @Test
    public void shouldMapCreateRequestToProduct(){
        CreateProductRequest request = CreateProductRequest.builder()
                .name("Product Name")
                .description("Product Description")
                .price(BigDecimal.valueOf(100))
                .image("Product Image")
                .categoryId(1L)
                .build();
        Product response = productMapper.toProductFromCreateRequest(request);
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getDescription(), response.getDescription());
        assertEquals(request.getPrice(), response.getPrice());
        assertEquals(request.getImage(), response.getImage());
        assertEquals(request.getCategoryId(), response.getCategory().getId());
    }

    @Test
    public void shouldMapUpdateRequestToProduct(){
        UpdateProductRequest request = UpdateProductRequest.builder()
                .name("Product Name")
                .description("Product Description")
                .price(BigDecimal.valueOf(100))
                .image("Product Image")
                .categoryId(1L)
                .build();
        Product response = productMapper.toProductFromUpdateRequest(request);
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getDescription(), response.getDescription());
        assertEquals(request.getPrice(), response.getPrice());
        assertEquals(request.getImage(), response.getImage());
        assertEquals(request.getCategoryId(), response.getCategory().getId());
    }

}
