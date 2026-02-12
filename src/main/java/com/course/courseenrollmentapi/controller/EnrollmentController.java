package com.course.courseenrollmentapi.controller;

import com.course.courseenrollmentapi.dto.*;
import com.course.courseenrollmentapi.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enrollStudent(@Valid @RequestBody EnrollmentRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.enrollStudent(dto));
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<PagedResponse<CourseListResponseDTO>> getCoursesByStudentId(
            @PathVariable Long studentId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "courseTitle") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return ResponseEntity.ok(enrollmentService.getCoursesByStudentId(studentId, page, size, sortBy, sortDir));
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<PagedResponse<StudentResponseDTO>> getStudentsByCourseId(
            @PathVariable Long courseId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "studentName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return ResponseEntity.ok(enrollmentService.getStudentsByCourseId(courseId, page, size, sortBy, sortDir));
    }
}
