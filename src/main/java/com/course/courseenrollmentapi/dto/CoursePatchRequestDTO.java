package com.course.courseenrollmentapi.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CoursePatchRequestDTO {

    private String courseTitle;

    @Positive(message = "Price must be greater than zero")
    private Double coursePrice;
    private Long categoryId;
}
