package com.course.courseenrollmentapi.dto;

import com.course.courseenrollmentapi.enums.Level;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetailResponseDTO {

    private Long detailId;
    private List<String> syllabus;
    private String duration;
    private Level level;
    private Long courseId;
    private String courseTitle;
}
