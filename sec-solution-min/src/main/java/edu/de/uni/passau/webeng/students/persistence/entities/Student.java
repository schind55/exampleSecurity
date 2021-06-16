package edu.de.uni.passau.webeng.students.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Student {

    @Id
    private Long matrNr;
    private String firstName;
    private String lastName;

    @ManyToMany
    private Collection<Course> courses = new LinkedList<>();

    @ManyToMany
    private Collection<Course> finishedCourses = new LinkedList<>();

    public Student() {
    }

    public Student(Long matrNr, String firstName, String lastName) {
        this.matrNr = matrNr;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(Long matrNr) {
        this.matrNr = matrNr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void addCourses(List<Course> course) {
        this.courses.addAll(course);
    }

    public Collection<Course> getFinishedCourses() {
        return finishedCourses;
    }

    public void setFinishedCourses(List<Course> finishedCourses) {
        this.finishedCourses = finishedCourses;
    }

    public void addFinishedCourses(List<Course> finishedCourses) {
        this.finishedCourses.addAll(finishedCourses);
    }
}