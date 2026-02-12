package com.course.courseenrollmentapi.repository;

import com.course.courseenrollmentapi.entity.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByStudentStudentIdAndCourseCourseId(Long studentId, Long courseId);
    Page<Enrollment> findByStudentStudentId(Long studentId, Pageable pageable);
    Page<Enrollment> findByCourseCourseId(Long courseId, Pageable pageable);
}
