package edu.de.uni.passau.webeng.students.application.service;

import edu.de.uni.passau.webeng.students.application.exception.MissingPrerequisiteException;
import edu.de.uni.passau.webeng.students.application.exception.NotFoundException;
import edu.de.uni.passau.webeng.students.persistence.entities.Course;
import edu.de.uni.passau.webeng.students.persistence.entities.Student;
import edu.de.uni.passau.webeng.students.persistence.repository.CourseRepository;
import edu.de.uni.passau.webeng.students.persistence.repository.StudentRepository;
import edu.de.uni.passau.webeng.students.web.dto.CourseDto;
import edu.de.uni.passau.webeng.students.web.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public StudentDto getStudent(Long id) {

	    // Find and return the student of given id
        Student student = studentRepository.getOne(id);
        if (student == null)
            throw new NotFoundException("Student", id);
        return convertToDto(student);
    }

    public List<CourseDto> getCoursesOfStudent(Long id) {

	    // Find the student of given id
        Student student = studentRepository.getOne(id);
        if (student == null)
            throw new NotFoundException("Student", id);

        List<CourseDto> courses = new LinkedList<>();
        for (Course course : student.getCourses()) {
            courses.add(convertToDto(course));
        }
        return courses;
    }

    public void registerForCourse(Long id, String cid) {

	    // Find the student of given id
        Student student = studentRepository.getOne(id);
        if (student == null)
            throw new NotFoundException("Student", id);

        // Find the course of given id
        Course course = courseRepository.getOne(cid);
        if (course == null)
            throw new NotFoundException("Course", cid);

        // Fail if prerequisite is not finished
        for (Course prerequisite : course.getPrerequisites()) {
            if (!student.getFinishedCourses().contains(prerequisite))
                throw new MissingPrerequisiteException(prerequisite.getTitle());
        }

        // Register for the course
        student.addCourse(course);

        studentRepository.save(student);
    }

    public List<StudentDto> getStundentsWithMoreThanXCourses(Integer numberOfCourses) {

        List<Student> students = studentRepository.getStudentsWithMoreCoursesThan(numberOfCourses);

        List<StudentDto> studentDtos = new LinkedList<>();
        for (Student student : students) {
            studentDtos.add(convertToDto(student));
        }
        return studentDtos;
    }

    public StudentDto convertToDto(Student student) {
	    StudentDto dto = new StudentDto();
        dto.setMatrNr(student.getMatrNr());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());

        return dto;
    }

    public CourseDto convertToDto(Course course) {
	    CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());

        // recursively get prerequisites
        List<CourseDto> prerequisites = new LinkedList<>();
        for (Course c : course.getPrerequisites()) {
            prerequisites.add(convertToDto(c));
        }
        dto.setPrerequisites(prerequisites);

        return dto;
    }
}