package com.course.courseenrollmentapi.service;

import com.course.courseenrollmentapi.dto.*;

public interface EnrollmentService {

    EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO dto);
    PagedResponse<CourseListResponseDTO> getCoursesByStudentId(Long studentId, Integer page, Integer size, String sortBy, String sortDir);
    PagedResponse<StudentResponseDTO> getStudentsByCourseId(Long courseId, Integer page, Integer size, String sortBy, String sortDir);

}
