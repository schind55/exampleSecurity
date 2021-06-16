package edu.de.uni.passau.webeng.students.web.controller;

import edu.de.uni.passau.webeng.students.spring.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    // Redirect to login template
    @GetMapping("/login")
    public String login() {
       return "custom-login";
    }

    // Login form with error
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "custom-login";
    }

    @GetMapping("/token")
    @ResponseBody
    public ResponseEntity<String> token(Principal principal) {
        String token = jwtTokenUtil.generateAccessToken((User)((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(token);
    }
}