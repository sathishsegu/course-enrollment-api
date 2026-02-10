package com.course.courseenrollmentapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {

    @NotBlank(message = "Category name should not be empty")
    private String categoryName;

    @NotBlank(message = "Category description should not be empty")
    @Size(min = 10, max = 255)
    private String categoryDescription;
}
