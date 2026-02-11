package com.course.courseenrollmentapi.controller;

import com.course.courseenrollmentapi.dto.CourseDetailUpdateRequestDTO;
import com.course.courseenrollmentapi.dto.CourseDetailRequestDTO;
import com.course.courseenrollmentapi.dto.CourseDetailResponseDTO;
import com.course.courseenrollmentapi.service.CourseDetailService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course-details")
public class CourseDetailController {

    private final CourseDetailService courseDetailService;

    public CourseDetailController(CourseDetailService courseDetailService) {
        this.courseDetailService = courseDetailService;
    }

    @PostMapping
    public ResponseEntity<CourseDetailResponseDTO> createCourseDetail(@Valid @RequestBody CourseDetailRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseDetailService.createCourseDetail(dto));
    }

    @GetMapping("/course/{detailId}")
    public ResponseEntity<CourseDetailResponseDTO> getCourseDetailsByDetailId(@PathVariable Long detailId) {
        return ResponseEntity.ok(courseDetailService.getCourseDetailByDetailId(detailId));
    }

    @PutMapping("/{detailId}")
    public ResponseEntity<CourseDetailResponseDTO> updateCourseDetails(@PathVariable Long detailId, @Valid @RequestBody CourseDetailUpdateRequestDTO dto) {
        return ResponseEntity.ok(courseDetailService.updateCourseDetail(detailId, dto));
    }

    @PatchMapping("/{detailId}")
    public ResponseEntity<CourseDetailResponseDTO> patchCourseDetails(@PathVariable Long detailId, @Valid @RequestBody CourseDetailUpdateRequestDTO dto) {
        return ResponseEntity.ok(courseDetailService.patchCourseDetail(detailId, dto));
    }
}
