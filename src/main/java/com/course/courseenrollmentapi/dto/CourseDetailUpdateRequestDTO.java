package com.course.courseenrollmentapi.dto;

import com.course.courseenrollmentapi.enums.Level;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetailUpdateRequestDTO {

    private List<String> syllabus;
    private String duration;
    private Level level;
}
