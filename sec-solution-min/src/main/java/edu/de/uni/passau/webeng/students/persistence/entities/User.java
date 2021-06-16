package edu.de.uni.passau.webeng.students.persistence.entities;

import edu.de.uni.passau.webeng.students.application.Roles;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Entity
public class User {

    @Id
    private String username;
    private String password;

    @OneToOne(optional = true)
    private Student student;

    @ElementCollection
    private List<Roles> roles;

    public User(String username, String password, Student student, Roles... roles) {
        this.username = username;
        this.password = password;
        this.student = student;
        this.roles = new ArrayList<>(Arrays.asList(roles));
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }


    public Optional<Student> getStudent() {
        return Optional.ofNullable(student);
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
