package com.course.courseenrollmentapi.config;

import com.course.courseenrollmentapi.dto.CourseListResponseDTO;
import com.course.courseenrollmentapi.dto.CourseResponseDTO;
import com.course.courseenrollmentapi.entity.Course;
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

        return modelMapper;
    }
}
