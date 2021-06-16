package edu.de.uni.passau.webeng.students.web.controller;

import edu.de.uni.passau.webeng.students.application.service.StudentService;
import edu.de.uni.passau.webeng.students.persistence.repository.UserRepository;
import edu.de.uni.passau.webeng.students.web.dto.StudentDto;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private StudentService studentService;

    @Test
    public void getStudentTest() throws Exception {
        StudentDto studentDto = new StudentDto();
        studentDto.setMatrNr(34622L);

        // Mit BDDMockito static helper function (Human readable)
        given(studentService.getStudent(34622L)).willReturn(studentDto);

        mockMvc.perform(get("/students/34622/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.matrNr", Matchers.equalTo(34622)));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void registerForCourseTest() throws Exception {
        mockMvc.perform(post("/students/34622/courses/c3"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
