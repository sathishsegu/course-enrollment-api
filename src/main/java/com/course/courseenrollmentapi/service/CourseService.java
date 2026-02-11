package com.course.courseenrollmentapi.service;

import com.course.courseenrollmentapi.dto.*;

public interface CourseService {
    CourseResponseDTO createCourse(CourseRequestDTO dto);
    CourseResponseDTO getCourseById(Long courseId);
    PagedResponse<CourseListResponseDTO> getAllCourses(Integer page, Integer size, String sortBy, String sortDir);
    PagedResponse<CourseListResponseDTO> getCoursesByCategory(Long categoryId, Integer page, Integer size, String sortBy, String sortDir);
    CourseResponseDTO updateCourse(Long courseId, CourseRequestDTO dto);
    CourseResponseDTO patchCourse(Long courseId, CoursePatchRequestDTO dto);
    void deleteCourse(Long courseId);
}
