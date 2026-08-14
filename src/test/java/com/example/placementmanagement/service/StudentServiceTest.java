package com.example.placementmanagement.service;

import com.example.placementmanagement.dto.request.StudentRequestDto;
import com.example.placementmanagement.dto.response.StudentResponseDto;
import com.example.placementmanagement.entity.Student;
import com.example.placementmanagement.exception.DuplicateStudentException;
import com.example.placementmanagement.exception.StudentNotFoundException;
import com.example.placementmanagement.repository.SkillRepository;
import com.example.placementmanagement.repository.StudentRepository;
import com.example.placementmanagement.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private StudentRequestDto studentRequestDto;
    private Student student;

    @BeforeEach
    void setUp() {
        studentRequestDto = new StudentRequestDto();
        studentRequestDto.setName("Rahul Sharma");
        studentRequestDto.setEmail("rahul.sharma@example.com");
        studentRequestDto.setPhone("9876543210");
        studentRequestDto.setDepartment("Computer Science");
        studentRequestDto.setCourseBranch("B.Tech CSE");
        studentRequestDto.setCgpa(8.85);
        studentRequestDto.setGraduationYear(2026);
        studentRequestDto.setSkills(Set.of("Java", "Spring Boot"));

        student = new Student();
        student.setId(1L);
        student.setName("Rahul Sharma");
        student.setEmail("rahul.sharma@example.com");
        student.setPhone("9876543210");
        student.setDepartment("Computer Science");
        student.setCourseBranch("B.Tech CSE");
        student.setCgpa(8.85);
        student.setGraduationYear(2026);
        student.setActive(true);
    }

    @Test
    @DisplayName("Should successfully create student when data is valid")
    void createStudent_Success() {
        when(studentRepository.existsByEmail(anyString())).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentResponseDto response = studentService.createStudent(studentRequestDto);

        assertNotNull(response);
        assertEquals("Rahul Sharma", response.getName());
        assertEquals("rahul.sharma@example.com", response.getEmail());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    @DisplayName("Should throw DuplicateStudentException when email already exists")
    void createStudent_DuplicateEmail_ThrowsException() {
        when(studentRepository.existsByEmail("rahul.sharma@example.com")).thenReturn(true);

        assertThrows(DuplicateStudentException.class, () -> studentService.createStudent(studentRequestDto));
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    @DisplayName("Should return student DTO when student exists by ID")
    void getStudentById_Success() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentResponseDto response = studentService.getStudentById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Rahul Sharma", response.getName());
    }

    @Test
    @DisplayName("Should throw StudentNotFoundException when student ID does not exist")
    void getStudentById_NotFound_ThrowsException() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentById(99L));
    }
}
