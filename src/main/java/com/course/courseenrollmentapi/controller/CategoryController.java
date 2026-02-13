package com.course.courseenrollmentapi.controller;

import com.course.courseenrollmentapi.dto.*;
import com.course.courseenrollmentapi.service.CategoryService;
import com.course.courseenrollmentapi.service.CourseService;
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
    private final CourseService courseService;

    /* Constructor Injection */
    public CategoryController(CategoryService categoryService, CourseService courseService) {
        this.categoryService = categoryService;
        this.courseService = courseService;
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
            description = "Retrieve all categories (Paginated)"
    )
    @GetMapping
    public ResponseEntity<PagedResponse<CategoryListResponseDTO>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "categoryName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories(page, size, sortBy, sortDir));
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


    /* RETRIEVE COURSES USING CATEGORY ID */
    @Operation(
            summary = "Retrieve Course by category",
            description = "Retrieves a (Paginated) course list for particular category using categoryId"
    )
    @GetMapping("/{categoryId}/courses")
    public ResponseEntity<PagedResponse<CourseListResponseDTO>> getCoursesByCategoryId(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "courseTitle") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCoursesByCategory(categoryId, page, size, sortBy, sortDir));
    }
}
