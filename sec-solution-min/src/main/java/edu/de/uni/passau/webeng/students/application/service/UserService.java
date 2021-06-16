package edu.de.uni.passau.webeng.students.application.service;

import edu.de.uni.passau.webeng.students.application.Roles;
import edu.de.uni.passau.webeng.students.application.exception.NotFoundException;
import edu.de.uni.passau.webeng.students.persistence.entities.Student;
import edu.de.uni.passau.webeng.students.persistence.entities.User;
import edu.de.uni.passau.webeng.students.persistence.repository.UserRepository;
import edu.de.uni.passau.webeng.students.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, StudentService studentService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.studentService = studentService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto getUser(String name) {

        // Find and return the student of given id
        User user = userRepository.getOne(name);
        if (user == null)
            throw new NotFoundException("User", name);
        return convertToDto(user);
    }

    public void createUser(String name, String password, Student student, Roles... roles) {
        User user = new User(name, passwordEncoder.encode(password), student, roles);
        userRepository.save(user);
    }

    public UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getUsername());
        Optional<Student> optionalStudent = user.getStudent();
        optionalStudent.ifPresent(student -> dto.setStudent(studentService.convertToDto(student)));
        dto.setRoles(user.getRoles());
        return dto;
    }
}
