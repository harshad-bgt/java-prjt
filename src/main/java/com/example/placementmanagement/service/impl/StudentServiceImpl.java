package com.example.placementmanagement.service.impl;

import com.example.placementmanagement.dto.request.StudentRequestDto;
import com.example.placementmanagement.dto.response.StudentResponseDto;
import com.example.placementmanagement.entity.Skill;
import com.example.placementmanagement.entity.Student;
import com.example.placementmanagement.exception.DuplicateStudentException;
import com.example.placementmanagement.exception.StudentNotFoundException;
import com.example.placementmanagement.mapper.StudentMapper;
import com.example.placementmanagement.repository.SkillRepository;
import com.example.placementmanagement.repository.StudentRepository;
import com.example.placementmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public StudentResponseDto createStudent(StudentRequestDto dto) {
        // Check if student email already exists
        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateStudentException(dto.getEmail());
        }

        // Process student skills
        Set<Skill> skillSet = new HashSet<>();
        if (dto.getSkills() != null) {
            for (String skillName : dto.getSkills()) {
                Skill skill = skillRepository.findByNameIgnoreCase(skillName.trim())
                    .orElseGet(() -> skillRepository.save(new Skill(skillName.trim())));
                skillSet.add(skill);
            }
        }

        Student student = StudentMapper.toEntity(dto, skillSet);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.toDto(savedStudent);
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));
        return StudentMapper.toDto(student);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto dto) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));

        // Check if updating to an existing email belonging to someone else
        if (!student.getEmail().equalsIgnoreCase(dto.getEmail()) && studentRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateStudentException(dto.getEmail());
        }

        Set<Skill> skillSet = new HashSet<>();
        if (dto.getSkills() != null) {
            for (String skillName : dto.getSkills()) {
                Skill skill = skillRepository.findByNameIgnoreCase(skillName.trim())
                    .orElseGet(() -> skillRepository.save(new Skill(skillName.trim())));
                skillSet.add(skill);
            }
        }

        StudentMapper.updateEntity(student, dto, skillSet);
        Student updatedStudent = studentRepository.save(student);
        return StudentMapper.toDto(updatedStudent);
    }

    @Override
    public void deactivateStudent(Long id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));
        student.setActive(false);
        studentRepository.save(student);
    }

    @Override
    public List<StudentResponseDto> getAllStudents(String search, String department) {
        List<Student> students;
        if (search != null && !search.trim().isEmpty()) {
            students = studentRepository.findByNameContainingIgnoreCase(search.trim());
        } else if (department != null && !department.trim().isEmpty()) {
            students = studentRepository.findByDepartmentIgnoreCase(department.trim());
        } else {
            students = studentRepository.findAll();
        }

        return students.stream()
            .map(StudentMapper::toDto)
            .collect(Collectors.toList());
    }
}
