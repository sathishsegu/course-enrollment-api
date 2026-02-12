package com.course.courseenrollmentapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequestDTO {

    @NotNull(message = "Student ID can't be null")
    private Long studentId;

    @NotNull(message = "Course ID can't be null")
    private Long courseId;
}
