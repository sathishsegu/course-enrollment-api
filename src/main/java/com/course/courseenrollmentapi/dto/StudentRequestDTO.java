package com.course.courseenrollmentapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentRequestDTO {

    @NotBlank(message = "student name can't be empty")
    private String studentName;

    @Email(message = "email should be in the email format")
    @NotBlank(message = "email can't be empty")
    private String email;
}
