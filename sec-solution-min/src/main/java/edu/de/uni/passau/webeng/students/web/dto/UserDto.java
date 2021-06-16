package edu.de.uni.passau.webeng.students.web.dto;

import edu.de.uni.passau.webeng.students.application.Roles;

import java.util.List;

public class UserDto {
    private String name;
    private StudentDto student;
    private List<Roles> roles;

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }
}
