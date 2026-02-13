package com.course.courseenrollmentapi.controller;

import com.course.courseenrollmentapi.dto.CourseDetailUpdateRequestDTO;
import com.course.courseenrollmentapi.dto.CourseDetailRequestDTO;
import com.course.courseenrollmentapi.dto.CourseDetailResponseDTO;
import com.course.courseenrollmentapi.service.CourseDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Course Details",
        description = "APIs for managing the course details"
)
@RestController
@RequestMapping("/api/course-details")
public class CourseDetailController {

    private final CourseDetailService courseDetailService;

    public CourseDetailController(CourseDetailService courseDetailService) {
        this.courseDetailService = courseDetailService;
    }


    @Operation(
            summary = "Create Course Details",
            description = "Adds the details to a Course"
    )
    @PostMapping
    public ResponseEntity<CourseDetailResponseDTO> createCourseDetail(@Valid @RequestBody CourseDetailRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseDetailService.createCourseDetail(dto));
    }


    @Operation(
            summary = "Retrieve Course Details By Course Details ID",
            description = "Retrieves the course details"
    )
    @GetMapping("/{detailId}")
    public ResponseEntity<CourseDetailResponseDTO> getCourseDetailsByDetailId(@PathVariable Long detailId) {
        return ResponseEntity.ok(courseDetailService.getCourseDetailByDetailId(detailId));
    }


    @Operation(
            summary = "Updates the course details",
            description = "Full updates for course details using PUT method"
    )
    @PutMapping("/{detailId}")
    public ResponseEntity<CourseDetailResponseDTO> updateCourseDetails(@PathVariable Long detailId, @Valid @RequestBody CourseDetailUpdateRequestDTO dto) {
        return ResponseEntity.ok(courseDetailService.updateCourseDetail(detailId, dto));
    }


    @Operation(
            summary = "Partial update for Course Details",
            description = "Updates the course details only required fields"
    )
    @PatchMapping("/{detailId}")
    public ResponseEntity<CourseDetailResponseDTO> patchCourseDetails(@PathVariable Long detailId, @Valid @RequestBody CourseDetailUpdateRequestDTO dto) {
        return ResponseEntity.ok(courseDetailService.patchCourseDetail(detailId, dto));
    }
}
