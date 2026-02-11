package com.course.courseenrollmentapi.dto;

import lombok.Data;

@Data
public class CourseListResponseDTO {

    private Long courseId;
    private String courseTitle;
    private Double coursePrice;
    private String categoryName;
}
