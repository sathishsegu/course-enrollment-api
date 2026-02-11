package com.course.courseenrollmentapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseRequestDTO {

    @NotBlank(message = "Category name should not be empty")
    @Size(max = 255)
    private String courseTitle;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than Zero")
    private Double coursePrice;

    @NotNull(message = "Category Id is required")
    private Long categoryId;
}
