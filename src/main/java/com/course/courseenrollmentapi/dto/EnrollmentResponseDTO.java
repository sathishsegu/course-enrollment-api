package com.course.courseenrollmentapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnrollmentResponseDTO {

    private Long enrollmentId;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseTitle;
    private LocalDateTime enrolledAt;
}
