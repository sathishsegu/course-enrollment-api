package com.course.courseenrollmentapi.repository;

import com.course.courseenrollmentapi.entity.CourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long> {

    Optional<CourseDetail> findByCourseCourseId(Long courseId);
}
