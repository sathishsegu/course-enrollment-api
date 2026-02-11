package com.course.courseenrollmentapi.service.impl;

import com.course.courseenrollmentapi.dto.CourseDetailUpdateRequestDTO;
import com.course.courseenrollmentapi.dto.CourseDetailRequestDTO;
import com.course.courseenrollmentapi.dto.CourseDetailResponseDTO;
import com.course.courseenrollmentapi.entity.Course;
import com.course.courseenrollmentapi.entity.CourseDetail;
import com.course.courseenrollmentapi.exception.ResourceConflictException;
import com.course.courseenrollmentapi.exception.ResourceNotFoundException;
import com.course.courseenrollmentapi.repository.CourseDetailRepository;
import com.course.courseenrollmentapi.repository.CourseRepository;
import com.course.courseenrollmentapi.service.CourseDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CourseDetailServiceImpl implements CourseDetailService {

    private final CourseDetailRepository courseDetailRepository;
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;

    public CourseDetailServiceImpl(
            CourseDetailRepository courseDetailRepository,
            ModelMapper modelMapper,
            CourseRepository courseRepository
    ) {
        this.courseDetailRepository = courseDetailRepository;
        this.modelMapper = modelMapper;
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDetailResponseDTO createCourseDetail(CourseDetailRequestDTO dto) {

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with id - " + dto.getCourseId()));

        if(courseDetailRepository.findByCourseCourseId(dto.getCourseId()).isPresent()) {
            throw new ResourceConflictException("Detail already exist for this course");
        }

        CourseDetail courseDetail = modelMapper.map(dto, CourseDetail.class);
        courseDetail.setCourse(course);

        CourseDetail savedDetail = courseDetailRepository.save(courseDetail);

        return modelMapper.map(savedDetail, CourseDetailResponseDTO.class);
    }

    @Override
    public CourseDetailResponseDTO getCourseDetailByDetailId(Long detailId) {
        CourseDetail courseDetail = courseDetailRepository.findById(detailId)
                .orElseThrow(() -> new ResourceNotFoundException("Course Details not found with detail id - " + detailId));

        return modelMapper.map(courseDetail, CourseDetailResponseDTO.class);
    }

    @Override
    public CourseDetailResponseDTO updateCourseDetail(Long detailId, CourseDetailUpdateRequestDTO dto) {

        CourseDetail existingCourseDetail = courseDetailRepository.findById(detailId)
                .orElseThrow(() -> new ResourceNotFoundException("Course details not found with id - " + detailId));

        existingCourseDetail.setDuration(dto.getDuration());
        existingCourseDetail.setLevel(dto.getLevel());
        existingCourseDetail.setSyllabus(dto.getSyllabus());

        CourseDetail updatedCourseDetail = courseDetailRepository.save(existingCourseDetail);

        return modelMapper.map(updatedCourseDetail, CourseDetailResponseDTO.class);
    }

    @Override
    public CourseDetailResponseDTO patchCourseDetail(Long detailId, CourseDetailUpdateRequestDTO dto) {
        CourseDetail existingCourseDetail = courseDetailRepository.findById(detailId)
                .orElseThrow(() -> new ResourceNotFoundException("Course detail not found with id - " + detailId));

        if(dto.getSyllabus() != null) {
            existingCourseDetail.setSyllabus(dto.getSyllabus());
        }

        if(dto.getDuration() != null) {
            existingCourseDetail.setDuration(dto.getDuration());
        }

        if(dto.getLevel() != null) {
            existingCourseDetail.setLevel(dto.getLevel());
        }

        CourseDetail updatedCourseDetail = courseDetailRepository.save(existingCourseDetail);
        return modelMapper.map(updatedCourseDetail, CourseDetailResponseDTO.class);
    }


}
