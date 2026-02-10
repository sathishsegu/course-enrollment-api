package com.course.courseenrollmentapi.dto;

import lombok.Data;

@Data
public class CategoryDetailResponseDTO {

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;
}
