package com.course.courseenrollmentapi.controller;

import com.course.courseenrollmentapi.dto.CourseListResponseDTO;
import com.course.courseenrollmentapi.dto.PagedResponse;
import com.course.courseenrollmentapi.dto.StudentRequestDTO;
import com.course.courseenrollmentapi.dto.StudentResponseDTO;
import com.course.courseenrollmentapi.service.EnrollmentService;
import com.course.courseenrollmentapi.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Students",
        description = "APIs for managing the students"
)
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final EnrollmentService enrollmentService;


    public StudentController(StudentService studentService, EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
    }


    @Operation(
            summary = "Creates a Student"
    )
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(dto));
    }


    @Operation(
            summary = "Get Student by ID",
            description = "Retrieves student using Student ID"
    )
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }


    @Operation(
            summary = "Get all Students",
            description = "Retrieves all students (Paginated)"
    )
    @GetMapping
    public ResponseEntity<PagedResponse<StudentResponseDTO>> getAllStudents(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "studentName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return ResponseEntity.ok(studentService.getAllStudents(page, size, sortBy, sortDir));
    }


    @Operation(
            summary = "Updates Student",
            description = "Full Updates the Student"
    )
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long studentId, @Valid @RequestBody StudentRequestDTO dto) {
        return ResponseEntity.ok(studentService.updateStudent(studentId, dto));
    }


    @Operation(
            summary = "Delete Student",
            description = "Deletes student using their Student ID"
    )
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @Operation(
            summary = "Get Courses using Student ID",
            description = "Retrieves student enrolled courses"
    )
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
}
