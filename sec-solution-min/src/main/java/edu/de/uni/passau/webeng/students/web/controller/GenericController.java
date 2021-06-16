package edu.de.uni.passau.webeng.students.web.controller;

import edu.de.uni.passau.webeng.students.application.service.UserService;
import edu.de.uni.passau.webeng.students.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

// Example class for property access
@RestController
@PropertySource("classpath:config.properties")
public class GenericController {

    private final UserService userService;

//    2) Alternative property access
//    @Value("${name}")
//    private String name;

    //    1) Accessing property data in config.properties
    private final Environment env;

    @Autowired
    public GenericController(UserService userService, Environment env) {
        this.userService = userService;
        this.env = env;
    }

    @GetMapping(path = "/name")
    public String getName() {
        return env.getProperty("name");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/principal")
    public String getPrincipal(Principal principal) {
        return principal.getName();
    }

    @GetMapping(path = "/user")
    public UserDto getUser(Principal principal) {
        return userService.getUser(principal.getName());
    }
}
