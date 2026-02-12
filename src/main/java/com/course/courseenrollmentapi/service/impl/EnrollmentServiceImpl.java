package com.course.courseenrollmentapi.service.impl;

import com.course.courseenrollmentapi.dto.*;
import com.course.courseenrollmentapi.entity.Course;
import com.course.courseenrollmentapi.entity.Enrollment;
import com.course.courseenrollmentapi.entity.Student;
import com.course.courseenrollmentapi.exception.ResourceConflictException;
import com.course.courseenrollmentapi.exception.ResourceNotFoundException;
import com.course.courseenrollmentapi.repository.CourseRepository;
import com.course.courseenrollmentapi.repository.EnrollmentRepository;
import com.course.courseenrollmentapi.repository.StudentRepository;
import com.course.courseenrollmentapi.service.EnrollmentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            CourseRepository courseRepository,
            StudentRepository studentRepository,
            ModelMapper modelMapper
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO dto) {

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID - " + dto.getStudentId()));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID - " + dto.getCourseId()));

        if(enrollmentRepository.existsByStudentStudentIdAndCourseCourseId(student.getStudentId(), course.getCourseId())) {
            throw new ResourceConflictException("Student is already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return modelMapper.map(savedEnrollment, EnrollmentResponseDTO.class);
    }

    @Override
    public PagedResponse<CourseListResponseDTO> getCoursesByStudentId(Long studentId, Integer page, Integer size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by("course." + sortBy).descending()
                : Sort.by("course." + sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Enrollment> enrollmentPage = enrollmentRepository.findByStudentStudentId(studentId, pageable);

        List<CourseListResponseDTO> content = enrollmentPage.getContent()
                .stream().map(enrollment -> modelMapper.map(enrollment.getCourse(), CourseListResponseDTO.class))
                .toList();

        return new PagedResponse<>(
                content,
                enrollmentPage.getNumber(),
                enrollmentPage.getSize(),
                enrollmentPage.getTotalElements(),
                enrollmentPage.getTotalPages(),
                enrollmentPage.isLast()
        );
    }

    @Override
    public PagedResponse<StudentResponseDTO> getStudentsByCourseId(Long courseId, Integer page, Integer size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by("student." + sortBy).descending()
                : Sort.by("student." + sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Enrollment> enrollmentPage = enrollmentRepository.findByCourseCourseId(courseId, pageable);

        List<StudentResponseDTO> content = enrollmentPage.getContent()
                .stream().map(enrollment -> modelMapper.map(enrollment.getStudent(), StudentResponseDTO.class))
                .toList();

        return new PagedResponse<>(
                content,
                enrollmentPage.getNumber(),
                enrollmentPage.getSize(),
                enrollmentPage.getTotalElements(),
                enrollmentPage.getTotalPages(),
                enrollmentPage.isLast()
        );
    }

}
