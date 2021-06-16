package edu.de.uni.passau.webeng.students.application.init;

import edu.de.uni.passau.webeng.students.application.Roles;
import edu.de.uni.passau.webeng.students.application.service.UserService;
import edu.de.uni.passau.webeng.students.persistence.entities.Course;
import edu.de.uni.passau.webeng.students.persistence.entities.Student;
import edu.de.uni.passau.webeng.students.persistence.repository.CourseRepository;
import edu.de.uni.passau.webeng.students.persistence.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class DataInit implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final UserService userService;

    @Autowired
    public DataInit(StudentRepository studentRepository, CourseRepository courseRepository, UserService userService) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {

        // Initializes the data of our model
        Course course0 = new Course("JSF", "Learn JSF.");
        Course course1 = new Course("Maven", "One of the most popular dependency management tools.");
        Course course2 = new Course("Web Servers", "Learn about web servers.");

        List<Course> courseList0 = new LinkedList<>();
        courseList0.add(course1);
        courseList0.add(course2);

        Course course3 = new Course("Spring Boot", "Use Spring Boot to bootstrap servers.");
        course3.setPrerequisites(courseList0);

        List<Course> courseList1 = new LinkedList<>();
        courseList1.addAll(courseList0);
        courseList1.add(course3);

        Course course4 = new Course("Spring Data", "A course about data persistence on the server.");
        course4.setPrerequisites(courseList1);

        List<Course> courseList2 = new LinkedList<>();
        courseList2.add(course0);
        courseList2.add(course1);

        List<Course> courseList3 = new LinkedList<>();
        courseList3.add(course3);

        List<Course> courseList4 = new LinkedList<>();
        courseList4.add(course0);

        // Repositories are only used for quick introduction write service methods for this instead
        courseRepository.save(course0);
        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        courseRepository.save(course4);

        Student student0 = new Student(23328L, "Max", "Muster");
        // Add to courses
//        courseList2.forEach(course -> course.addStudent(student0));
        student0.addCourses(courseList2);

        Student student1 = new Student(34622L, "Hans", "Muster");
        // Add to courses
//        courseList3.forEach(course -> course.addStudent(student1));
        student1.addCourses(courseList3);
        student1.addFinishedCourses(courseList0);

        Student student2 = new Student(48645L, "Alice", "Klint");
        // Add to courses
//        courseList4.forEach(course -> course.addStudent(student2));
        student2.addCourses(courseList4);
        student2.addFinishedCourses(courseList0);


        Student student3 = new Student(24232L, "Bob", "Ser");
        student3.addFinishedCourses(courseList1);

        studentRepository.save(student0);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        // Create Users: Better to work with services in general
        userService.createUser("admin", "123", null, Roles.ADMIN);
        userService.createUser("student", "abc", student0, Roles.USER);

        // User with no roles. Can only get data about himself.
        userService.createUser("dummy", "dummy", null);
    }
}
