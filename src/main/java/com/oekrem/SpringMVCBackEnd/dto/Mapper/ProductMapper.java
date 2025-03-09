package com.oekrem.SpringMVCBackEnd.dto.Mapper;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.ProductResponse;
import com.oekrem.SpringMVCBackEnd.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(source = "category.id", target = "category_id")
    ProductResponse toResponse(Product product);

    @Mapping(source = "categoryId", target = "category.id")
    Product toProductFromCreateRequest(CreateProductRequest createProductRequest);
    @Mapping(source = "categoryId", target = "category.id")
    Product toProductFromUpdateRequest(UpdateProductRequest updateProductRequest);

    @Mapping(target = "id", ignore = true)
    void patchProduct(UpdateProductRequest updateProductRequest, @MappingTarget Product product);

}
