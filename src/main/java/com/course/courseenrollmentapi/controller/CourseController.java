package com.course.courseenrollmentapi.controller;

import com.course.courseenrollmentapi.dto.*;
import com.course.courseenrollmentapi.service.CourseService;
import com.course.courseenrollmentapi.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "Courses",
        description = "APIs for managing the courses"
)
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    /* Constructor Injection */
    public CourseController(CourseService courseService, EnrollmentService enrollmentService) {
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }


    /* CREATION */
    @Operation(
            summary = "Create Course",
            description = "Creates a new course"
    )
    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(dto));
    }


    /* GET A PARTICULAR COURSE USING COURSE ID */
    @Operation(
            summary = "Retrieve Course",
            description = "Retrieves a particular Course using courseId"
    )
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(courseId));
    }


    /* GET ALL COURSES */
    @Operation(
            summary = "Retrieve all courses",
            description = "Retrieve all courses (Paginated)"
    )
    @GetMapping
    public ResponseEntity<PagedResponse<CourseListResponseDTO>> getAllCourses(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "5") Integer size,
        @RequestParam(defaultValue = "courseTitle") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir
    ) {

        return ResponseEntity.ok(courseService.getAllCourses(page, size, sortBy, sortDir));
    }



    /* UPDATE THE WHOLE COURSE */
    @Operation(
            summary = "Update Course",
            description = "Updated whole course using courseId, and request body"
    )
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponseDTO> updateCourseById(@PathVariable Long courseId, @Valid @RequestBody CourseRequestDTO dto) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, dto));
    }


    /* UPDATE THE PARTIAL COURSE */
    @Operation(
            summary = "Patch Course",
            description = "Updates the partial course details using courseId and patch request body"
    )
    @PatchMapping("/{courseId}")
    public ResponseEntity<CourseResponseDTO> patchCourse(@PathVariable Long courseId, @Valid @RequestBody CoursePatchRequestDTO dto) {
        return ResponseEntity.ok().body(courseService.patchCourse(courseId, dto));
    }


    /* DELETE COURSE */
    @Operation(
            summary = "Delete Course",
            description = "Removes a course from the DB using courseId"
    )
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
