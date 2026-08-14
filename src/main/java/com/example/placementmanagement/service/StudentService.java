package com.example.placementmanagement.service;

import com.example.placementmanagement.dto.request.StudentRequestDto;
import com.example.placementmanagement.dto.response.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto dto);
    StudentResponseDto getStudentById(Long id);
    StudentResponseDto updateStudent(Long id, StudentRequestDto dto);
    void deactivateStudent(Long id);
    List<StudentResponseDto> getAllStudents(String search, String department);
}
