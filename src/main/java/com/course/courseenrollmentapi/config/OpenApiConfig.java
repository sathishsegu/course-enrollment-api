package com.course.courseenrollmentapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI CourseEnrollmentOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Course Enrollment REST API")
                        .description("REST API for managing Categories, courses, students details with pagination, validation, and error handling.")
                        .version("v1.0"));
    }
}
