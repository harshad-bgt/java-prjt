package com.example.placementmanagement.controller;

import com.example.placementmanagement.dto.request.StudentRequestDto;
import com.example.placementmanagement.dto.response.StudentResponseDto;
import com.example.placementmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Create a new student
    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto dto) {
        StudentResponseDto response = studentService.createStudent(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
        StudentResponseDto response = studentService.getStudentById(id);
        return ResponseEntity.ok(response);
    }

    // Update student details
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDto dto) {
        StudentResponseDto response = studentService.updateStudent(id, dto);
        return ResponseEntity.ok(response);
    }

    // Soft delete / deactivate student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateStudent(@PathVariable Long id) {
        studentService.deactivateStudent(id);
        return ResponseEntity.noContent().build();
    }

    // Get all students with optional search/department filter
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(
        @RequestParam(required = false) String search,
        @RequestParam(required = false) String department
    ) {
        List<StudentResponseDto> students = studentService.getAllStudents(search, department);
        return ResponseEntity.ok(students);
    }
}
