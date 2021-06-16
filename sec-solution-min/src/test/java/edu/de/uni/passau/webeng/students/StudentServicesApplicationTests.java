package edu.de.uni.passau.webeng.students;

import edu.de.uni.passau.webeng.students.persistence.entities.Course;
import edu.de.uni.passau.webeng.students.persistence.repository.CourseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentServicesApplicationTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CourseRepository courseRepository;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

	@Test
    @WithMockUser(roles = {"ADMIN"})
	public void getStudentTest() throws Exception {
        mvc.perform(get("/students/34622/")).andExpect(status().isOk()).andExpect(content().json(
                "{\"matrNr\":34622,\"firstName\":\"Hans\",\"lastName\":\"Muster\"}"
        ));
	}

	@Test
    public void tryTest1() {
        courseRepository.save(new Course("test", "this is a test"));
        System.out.println(assertEquals("  ", "  "));
        int index = 1;
        System.out.println(courseRepository.findAll().get(index).getTitle());
        System.out.println(courseRepository.findAll().get(index).getDescription());
        System.out.println(assertEquals("test", courseRepository.findAll().get(0).getTitle()));
    }

    private boolean assertEquals(String s, String s1) {
         return (s.equals(s1));
    }

    @Test
    public void getStudentTestDenied() throws Exception {
        mvc.perform(get("/students/34622/")).andDo(print()).andExpect(status().isFound());
    }

	// Anfragen mit curl testen
    // curl -w "\n" localhost:8080/students/34622/

    // curl -X POST -w "\n" localhost:8080/students/34622/courses/
}
