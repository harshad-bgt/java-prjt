package com.example.placementmanagement.mapper;

import com.example.placementmanagement.dto.request.StudentRequestDto;
import com.example.placementmanagement.dto.response.StudentResponseDto;
import com.example.placementmanagement.entity.Skill;
import com.example.placementmanagement.entity.Student;

import java.util.Set;
import java.util.stream.Collectors;

public class StudentMapper {

    public static Student toEntity(StudentRequestDto dto, Set<Skill> skills) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setDepartment(dto.getDepartment());
        student.setCourseBranch(dto.getCourseBranch());
        student.setCgpa(dto.getCgpa());
        student.setGraduationYear(dto.getGraduationYear());
        student.setResumeInfo(dto.getResumeInfo());
        student.setActive(true);
        if (skills != null) {
            student.setSkills(skills);
        }
        return student;
    }

    public static void updateEntity(Student student, StudentRequestDto dto, Set<Skill> skills) {
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setDepartment(dto.getDepartment());
        student.setCourseBranch(dto.getCourseBranch());
        student.setCgpa(dto.getCgpa());
        student.setGraduationYear(dto.getGraduationYear());
        student.setResumeInfo(dto.getResumeInfo());
        if (skills != null) {
            student.setSkills(skills);
        }
    }

    public static StudentResponseDto toDto(Student student) {
        if (student == null) return null;
        StudentResponseDto dto = new StudentResponseDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        dto.setDepartment(student.getDepartment());
        dto.setCourseBranch(student.getCourseBranch());
        dto.setCgpa(student.getCgpa());
        dto.setGraduationYear(student.getGraduationYear());
        dto.setResumeInfo(student.getResumeInfo());
        dto.setActive(student.getActive());
        dto.setIsPlaced(student.getPlacement() != null);
        if (student.getSkills() != null) {
            dto.setSkills(student.getSkills().stream().map(Skill::getName).collect(Collectors.toSet()));
        }
        return dto;
    }
}
