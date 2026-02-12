package com.course.courseenrollmentapi.config;

import com.course.courseenrollmentapi.dto.CourseDetailResponseDTO;
import com.course.courseenrollmentapi.dto.CourseListResponseDTO;
import com.course.courseenrollmentapi.dto.CourseResponseDTO;
import com.course.courseenrollmentapi.dto.EnrollmentResponseDTO;
import com.course.courseenrollmentapi.entity.Course;
import com.course.courseenrollmentapi.entity.CourseDetail;
import com.course.courseenrollmentapi.entity.Enrollment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true);

        modelMapper.typeMap(Course.class, CourseResponseDTO.class)
                .addMapping(src -> src.getCategory().getCategoryId(),
                        CourseResponseDTO::setCategoryId)
                .addMapping(src -> src.getCategory().getCategoryName(),
                        CourseResponseDTO::setCategoryName);

        modelMapper.typeMap(Course.class, CourseListResponseDTO.class)
                .addMappings(mapper ->
                        mapper.map(
                                src -> src.getCategory().getCategoryName(),
                                CourseListResponseDTO::setCategoryName
                        )
                );

        modelMapper.typeMap(CourseDetail.class, CourseDetailResponseDTO.class)
                .addMapping(src -> src.getCourse().getCourseId(),
                        CourseDetailResponseDTO::setCourseId)
                .addMapping(src -> src.getCourse().getCourseTitle(),
                        CourseDetailResponseDTO::setCourseTitle);

        modelMapper.typeMap(Enrollment.class, EnrollmentResponseDTO.class)
                .addMapping(src -> src.getStudent().getStudentId(),
                        EnrollmentResponseDTO::setStudentId)
                .addMapping(src -> src.getStudent().getStudentName(),
                        EnrollmentResponseDTO::setStudentName)
                .addMapping(src -> src.getCourse().getCourseId(),
                        EnrollmentResponseDTO::setCourseId)
                .addMapping(src -> src.getCourse().getCourseTitle(),
                        EnrollmentResponseDTO::setCourseTitle);


        return modelMapper;
    }
}
