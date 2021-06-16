package edu.de.uni.passau.webeng.students.web.controller;

import edu.de.uni.passau.webeng.students.application.service.StudentService;
import edu.de.uni.passau.webeng.students.web.dto.CourseDto;
import edu.de.uni.passau.webeng.students.web.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/{id:[1-9]+[0-9]*}")
    public StudentDto getStudent(@PathVariable("id") Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping(path = "/{id:[1-9]+[0-9]*}/courses")
    public List<CourseDto> getCoursesOfStudent(@PathVariable("id") Long id) {
        return studentService.getCoursesOfStudent(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/{id:[1-9]+[0-9]*}/courses/{cid}")
    @ResponseStatus(HttpStatus.OK)
    public void registerForCourse(@PathVariable("id") Long id, @PathVariable("cid") String cid) {
        studentService.registerForCourse(id, cid);
    }

    @GetMapping(path = "/moreThanXCourses")
    public List<StudentDto> getStudentsWithMoreThanXCourses(
            @RequestParam("number") Integer number) {
        return studentService.getStundentsWithMoreThanXCourses(number);
    }
}
