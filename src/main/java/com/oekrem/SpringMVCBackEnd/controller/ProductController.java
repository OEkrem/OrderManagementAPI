package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateProductRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.ProductResponse;
import com.oekrem.SpringMVCBackEnd.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "Manages Product Operations")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class),
                            array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing parameters)")
    })
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long categoryId
    ){
        return ResponseEntity.ok(productService.findAll(page, size, categoryId));
    }

    @Operation(summary = "Get Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid product id format)"),
            @ApiResponse(responseCode = "404", description = "Product Not Found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Operation(summary = "Add Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing product details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            //@ApiResponse(responseCode = "403", description = "Forbidden, Only admins can be create a product"),
            @ApiResponse(responseCode = "409", description = "Conflict, Product could not be created")
    })
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody CreateProductRequest createProductRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(createProductRequest));
    }

    @Operation(summary = "Update Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing product details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            //@ApiResponse(responseCode = "403", description = "Forbidden, Only admins can update the product"),
            @ApiResponse(responseCode = "404", description = "Product Not Found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest updateProductRequest){
        return ResponseEntity.ok(productService.updateProduct(id, updateProductRequest));
    }

    @Operation(summary = "Patch Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing product details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            //@ApiResponse(responseCode = "403", description = "Forbidden, Only admins can patch the product"),
            @ApiResponse(responseCode = "404", description = "Product Not Found"),
    })
    @PatchMapping("{id}")
    public ResponseEntity<ProductResponse> patchProduct(@PathVariable Long id, @RequestBody PatchProductRequest patchProductRequest){
        return ResponseEntity.ok(productService.patchProduct(id, patchProductRequest));
    }

    @Operation(summary = "Delete Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid product id format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required"),
            //@ApiResponse(responseCode = "403", description = "Forbidden, Only admins can delete the product"),
            @ApiResponse(responseCode = "404", description = "Product Not Found"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
