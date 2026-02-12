package com.course.courseenrollmentapi.service.impl;

import com.course.courseenrollmentapi.dto.PagedResponse;
import com.course.courseenrollmentapi.dto.StudentRequestDTO;
import com.course.courseenrollmentapi.dto.StudentResponseDTO;
import com.course.courseenrollmentapi.entity.Student;
import com.course.courseenrollmentapi.exception.ResourceNotFoundException;
import com.course.courseenrollmentapi.repository.StudentRepository;
import com.course.courseenrollmentapi.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        Student student = modelMapper.map(dto, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentResponseDTO.class);
    }

    @Override
    public StudentResponseDTO getStudentById(Long studentId) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID - " + studentId));

        return modelMapper.map(existingStudent, StudentResponseDTO.class);
    }

    @Override
    public PagedResponse<StudentResponseDTO> getAllStudents(Integer page, Integer size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> studentPage = studentRepository.findAll(pageable);
        List<StudentResponseDTO> students = studentPage.getContent()
                .stream()
                .map(student -> modelMapper.map(student, StudentResponseDTO.class))
                .toList();

        return new PagedResponse<>(
                students,
                studentPage.getNumber(),
                studentPage.getSize(),
                studentPage.getTotalElements(),
                studentPage.getTotalPages(),
                studentPage.isLast()
        );
    }

    @Override
    public StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO dto) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID - " + studentId));

        modelMapper.map(dto, existingStudent);
        Student updatedStudent = studentRepository.save(existingStudent);

        return modelMapper.map(updatedStudent, StudentResponseDTO.class);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID - " + studentId));
        studentRepository.delete(existingStudent);
    }
}
