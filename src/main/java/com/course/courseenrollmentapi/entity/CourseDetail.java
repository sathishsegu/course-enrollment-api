package com.course.courseenrollmentapi.entity;

import com.course.courseenrollmentapi.enums.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course_details")
public class CourseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "course_syllabus",
            joinColumns = @JoinColumn(name = "detail_id")
    )
    @Column(name = "topic")
    private List<String> syllabus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;

    @Column(nullable = false)
    private String duration;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false, unique = true)
    private Course course;
}
