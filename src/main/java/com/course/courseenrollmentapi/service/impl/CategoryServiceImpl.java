package com.course.courseenrollmentapi.service.impl;

import com.course.courseenrollmentapi.dto.*;
import com.course.courseenrollmentapi.entity.Category;
import com.course.courseenrollmentapi.exception.CategoryNotFoundException;
import com.course.courseenrollmentapi.repository.CategoryRepository;
import com.course.courseenrollmentapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        Category category = modelMapper.map(dto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryResponseDTO.class);
    }

    @Override
    public CategoryDetailResponseDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                    "Category not found with id - " + categoryId));

        return modelMapper.map(category, CategoryDetailResponseDTO.class);
    }

    @Override
    public PagedResponse<CategoryListResponseDTO> getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<CategoryListResponseDTO> content = categoryPage.getContent()
                .stream()
                .map(category -> modelMapper.map(category, CategoryListResponseDTO.class))
                .toList();

        return new PagedResponse<>(
            content,
            categoryPage.getNumber(),
            categoryPage.getSize(),
            categoryPage.getTotalElements(),
            categoryPage.getTotalPages(),
            categoryPage.isLast()
        );
    }

    @Override
    public CategoryResponseDTO updateCategory(CategoryRequestDTO dto, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id - " + categoryId));

        modelMapper.map(dto, category);
        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryResponseDTO.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id - " + categoryId));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponseDTO patchCategory(Long categoryId, CategoryPatchRequestDTO dto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with ID - " + categoryId));

        if(dto.getCategoryName() != null) {
            category.setCategoryName(dto.getCategoryName());
        }

        if(dto.getCategoryDescription() != null) {
            category.setCategoryDescription(dto.getCategoryDescription());
        }

        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryResponseDTO.class);
    }

}
