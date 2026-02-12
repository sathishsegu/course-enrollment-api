package com.course.courseenrollmentapi.service;

import com.course.courseenrollmentapi.dto.PagedResponse;
import com.course.courseenrollmentapi.dto.StudentRequestDTO;
import com.course.courseenrollmentapi.dto.StudentResponseDTO;

public interface StudentService {

    StudentResponseDTO createStudent(StudentRequestDTO dto);
    StudentResponseDTO getStudentById(Long studentId);
    PagedResponse<StudentResponseDTO> getAllStudents(Integer page, Integer size, String sortBy, String sortDir);
    StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO dto);
    void deleteStudent(Long studentId);
}
