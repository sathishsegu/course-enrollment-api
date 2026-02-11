package com.course.courseenrollmentapi.service;

import com.course.courseenrollmentapi.dto.CourseDetailUpdateRequestDTO;
import com.course.courseenrollmentapi.dto.CourseDetailRequestDTO;
import com.course.courseenrollmentapi.dto.CourseDetailResponseDTO;

public interface CourseDetailService {

    CourseDetailResponseDTO createCourseDetail(CourseDetailRequestDTO dto);
    CourseDetailResponseDTO getCourseDetailByDetailId(Long detailId);
    CourseDetailResponseDTO updateCourseDetail(Long detailId, CourseDetailUpdateRequestDTO dto);
    CourseDetailResponseDTO patchCourseDetail(Long detailId, CourseDetailUpdateRequestDTO dto);
}
