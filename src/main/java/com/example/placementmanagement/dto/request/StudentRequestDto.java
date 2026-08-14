package com.example.placementmanagement.dto.request;

import jakarta.validation.constraints.*;
import java.util.Set;

public class StudentRequestDto {

    @NotBlank(message = "Student name cannot be empty")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9+\\-\\s()]{7,20}$", message = "Phone number must be valid")
    private String phone;

    @NotBlank(message = "Department cannot be empty")
    private String department;

    @NotBlank(message = "Course/Branch cannot be empty")
    private String courseBranch;

    @NotNull(message = "CGPA is required")
    @DecimalMin(value = "0.0", message = "CGPA must be at least 0.0")
    @DecimalMax(value = "10.0", message = "CGPA cannot exceed 10.0")
    private Double cgpa;

    @NotNull(message = "Graduation year is required")
    @Min(value = 2020, message = "Graduation year must be 2020 or later")
    @Max(value = 2035, message = "Graduation year must be reasonable")
    private Integer graduationYear;

    private String resumeInfo;

    private Set<String> skills;

    public StudentRequestDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourseBranch() {
        return courseBranch;
    }

    public void setCourseBranch(String courseBranch) {
        this.courseBranch = courseBranch;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getResumeInfo() {
        return resumeInfo;
    }

    public void setResumeInfo(String resumeInfo) {
        this.resumeInfo = resumeInfo;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
