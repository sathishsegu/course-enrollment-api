package com.course.courseenrollmentapi.dto;

import lombok.Data;

@Data
public class StudentResponseDTO {

    private Long studentId;
    private String studentName;
    private String email;
}
