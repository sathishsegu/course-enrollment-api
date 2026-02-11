package com.course.courseenrollmentapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseResponseDTO {

    private Long courseId;
    private String courseTitle;
    private Double coursePrice;
    private LocalDateTime createdAt;
    private Long categoryId;
    private String categoryName;
}
