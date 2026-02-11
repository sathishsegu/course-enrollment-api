package com.course.courseenrollmentapi.dto;

import com.course.courseenrollmentapi.enums.Level;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetailRequestDTO {

    @NotEmpty(message = "Syllabus must not be empty")
    private List<@NotBlank String> syllabus;

    @NotBlank(message = "Duration should not be empty")
    private String duration;

    @NotNull(message = "Level must not be null")
    private Level level;

    @NotNull
    private Long courseId;
}
