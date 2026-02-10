package com.course.courseenrollmentapi.dto;

import lombok.Data;

@Data
public class CategoryPatchRequestDTO {

    private String categoryName;
    private String categoryDescription;
}
