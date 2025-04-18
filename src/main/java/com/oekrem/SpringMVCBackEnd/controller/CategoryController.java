package com.oekrem.SpringMVCBackEnd.controller;

import com.oekrem.SpringMVCBackEnd.dto.Request.CreateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.PatchCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Request.UpdateCategoryRequest;
import com.oekrem.SpringMVCBackEnd.dto.Response.CategoryResponse;
import com.oekrem.SpringMVCBackEnd.dto.common.PageResponse;
import com.oekrem.SpringMVCBackEnd.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Tag(name = "Category Controller", description = "Manages category operations")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PageResponse.class),
                            array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid query parameters)"),
    })
    @GetMapping
    public ResponseEntity<PageResponse<CategoryResponse>> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(categoryService.findAll(page, size));
    }

    @Operation(summary = "Get Category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid category id format)"),
            @ApiResponse(responseCode = "404", description = "Category Not Found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @Operation(summary = "Add Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing category details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required or failed"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins can create a category"),
            @ApiResponse(responseCode = "409", description = "Conflict, New category could not be created")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CreateCategoryRequest createCategoryRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(createCategoryRequest));
    }

    @Operation(summary = "Update Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing category details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required or failed"),
            @ApiResponse(responseCode = "403", description = "Forbidden, Only Admins can update the category"),
            @ApiResponse(responseCode = "404", description = "Category Not Found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return ResponseEntity.ok(categoryService.updateCategory(id, updateCategoryRequest));
    }

    @Operation(summary = "Patch Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid or missing category details)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required or failed"),
            @ApiResponse(responseCode = "403", description = "Forbidden, User does not have permission to patch category"),
            @ApiResponse(responseCode = "404", description = "Category Not Found")
    })
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponse> patchCategory(@PathVariable Long id, @RequestBody PatchCategoryRequest patchCategoryRequest){
        return ResponseEntity.ok(categoryService.patchCategory(id, patchCategoryRequest));
    }

    @Operation(summary = "Delete Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request (Invalid category id format)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, Authentication is required or failed"),
            @ApiResponse(responseCode = "403", description = "Forbidden, User does not have permission to delete category"),
            @ApiResponse(responseCode = "404", description = "Category Not Found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
