package com.course.courseenrollmentapi.controller;

import com.course.courseenrollmentapi.dto.*;
import com.course.courseenrollmentapi.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Categories",
        description = "APIs for managing the categories"
)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    /* Constructor Injection */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    /* CREATION */
    @Operation(
            summary = "Create Category",
            description = "Creates a new category"
    )
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO dto) {
        CategoryResponseDTO responseDTO = categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    /* GET A PARTICULAR CATEGORY USING ID */
    @Operation(
            summary = "Get Category",
            description = "Retrieve a category using ID"
    )
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDetailResponseDTO> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(categoryId));
    }


    /* GET ALL CATEGORIES */
    @Operation(
            summary = "Get Category",
            description = "Retrieve all categories"
    )
    @GetMapping
    public ResponseEntity<PagedResponse<CategoryListResponseDTO>> getAllCategories(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "categoryName") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories(pageNumber, pageSize, sortBy, direction));
    }


    /* UPDATE THE WHOLE CATEGORY */
    @Operation(
            summary = "Update Category",
            description = "Update whole category using ID"
    )
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@Valid @RequestBody CategoryRequestDTO dto, @PathVariable Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(dto, categoryId));
    }


    /* UPDATE THE PARTIAL CATEGORY */
    @Operation(
            summary = "Patch Category",
            description = "Updates the partial category details using ID"
    )
    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> patchCategory(@PathVariable Long categoryId, @RequestBody CategoryPatchRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.patchCategory(categoryId, dto));
    }


    /* DELETE THE CATEGORY */
    @Operation(
            summary = "Delete Category",
            description = "Removes a category from the database using ID"
    )
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
