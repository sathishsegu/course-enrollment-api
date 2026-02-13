package com.course.courseenrollmentapi.repository;

import com.course.courseenrollmentapi.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findByCategoryCategoryId(Long categoryId, Pageable pageable);

    boolean existsByCategory_CategoryId(Long categoryId);

    @EntityGraph(attributePaths = {"courseDetail"})
    Optional<Course> findWithDetailByCourseId(Long courseId);
}
