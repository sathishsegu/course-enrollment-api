package com.course.courseenrollmentapi.service.impl;

import com.course.courseenrollmentapi.dto.*;
import com.course.courseenrollmentapi.entity.Category;
import com.course.courseenrollmentapi.entity.Course;
import com.course.courseenrollmentapi.exception.ResourceNotFoundException;
import com.course.courseenrollmentapi.repository.CategoryRepository;
import com.course.courseenrollmentapi.repository.CourseRepository;
import com.course.courseenrollmentapi.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id - " + dto.getCategoryId()));

        Course course = modelMapper.map(dto, Course.class);
        course.setCategory(category);

        Course savedCourse = courseRepository.save(course);

        return modelMapper.map(savedCourse, CourseResponseDTO.class);
    }

    @Override
    public CourseResponseDTO getCourseById(Long courseId) {

        Course course = courseRepository.findWithDetailByCourseId(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with id - " +courseId));



        return modelMapper.map(course, CourseResponseDTO.class);
    }

    @Override
    public PagedResponse<CourseListResponseDTO> getAllCourses(Integer page, Integer size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Course> coursePage = courseRepository.findAll(pageable);

        List<CourseListResponseDTO> content = coursePage.getContent()
                .stream()
                .map(course -> modelMapper.map(course, CourseListResponseDTO.class))
                .toList();

        return new PagedResponse<>(
                content,
                coursePage.getNumber(),
                coursePage.getSize(),
                coursePage.getTotalElements(),
                coursePage.getTotalPages(),
                coursePage.isLast()
        );
    }

    @Override
    public PagedResponse<CourseListResponseDTO> getCoursesByCategory(Long categoryId, Integer page, Integer size, String sortBy, String sortDir) {

        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with category id - " + categoryId));

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Course> coursePage = courseRepository.findByCategoryCategoryId(categoryId, pageable);

        List<CourseListResponseDTO> content = coursePage.getContent()
                .stream()
                .map(course -> modelMapper.map(course, CourseListResponseDTO.class))
                .toList();

        return new PagedResponse<>(
                content,
                coursePage.getNumber(),
                coursePage.getSize(),
                coursePage.getTotalElements(),
                coursePage.getTotalPages(),
                coursePage.isLast()
        );
    }

    @Override
    public CourseResponseDTO updateCourse(Long courseId, CourseRequestDTO dto) {

        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with id - " + courseId));

        Category category = categoryRepository.findById(dto.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Category not found with id - " + dto.getCategoryId()));

//        modelMapper.map(dto, existingCourse);
//        existingCourse.setCategory(category);

        existingCourse.setCourseTitle(dto.getCourseTitle());
        existingCourse.setCoursePrice(dto.getCoursePrice());
        existingCourse.setCategory(category);

// For updates, I prefer modifying the existing entity instead of remapping it, to avoid accidental field overwrites and to preserve audit fields like createdAt.

        Course updatedCourse = courseRepository.save(existingCourse);

        return modelMapper.map(updatedCourse, CourseResponseDTO.class);
    }

    @Override
    public CourseResponseDTO patchCourse(Long courseId, CoursePatchRequestDTO dto) {

        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course Not found with Id - " + courseId));

        if(dto.getCourseTitle() != null) {
            existingCourse.setCourseTitle(dto.getCourseTitle());
        }

        if(dto.getCoursePrice() != null) {
            existingCourse.setCoursePrice(dto.getCoursePrice());
        }

        if(dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category not found with id - " + dto.getCategoryId()));

            existingCourse.setCategory(category);
        }

        Course updatedCourse = courseRepository.save(existingCourse);

        return modelMapper.map(updatedCourse, CourseResponseDTO.class);
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with id - " + courseId));

        courseRepository.delete(course);
    }


}

