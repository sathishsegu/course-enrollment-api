package com.course.courseenrollmentapi.service;

import com.course.courseenrollmentapi.dto.*;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO dto);
    CategoryDetailResponseDTO getCategoryById(Long categoryId);
    PagedResponse<CategoryListResponseDTO> getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String direction);
    CategoryResponseDTO updateCategory(CategoryRequestDTO dto, Long categoryId);
    void deleteCategory(Long categoryId);
    CategoryResponseDTO patchCategory(Long categoryId, CategoryPatchRequestDTO dto);
}
