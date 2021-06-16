package edu.de.uni.passau.webeng.students;

import edu.de.uni.passau.webeng.students.persistence.entities.Employee;
import edu.de.uni.passau.webeng.students.persistence.entities.Location;
import edu.de.uni.passau.webeng.students.persistence.repository.EmployeeRepository;
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

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentServicesApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Autowired
    private EmployeeRepository employeeRepository;

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
    public void getStudentTestDenied() throws Exception {
        mvc.perform(get("/students/34622/")).andDo(print()).andExpect(status().isFound());
    }

    @Test
    public void create_Employee_And_Set_Different_Locations_Test() {
        assertNotNull(employeeRepository);
        int i = 5;
        long employeeId = UUID.randomUUID().getMostSignificantBits();
        String name = "Mathias";
        Date validityDate = new Date();
        validityDate.setTime(16416544);
        String password = "secret";
        Location currentLocation = new Location(154, 456, null, null, null);
        Employee employee1 = new Employee(name, validityDate, password, currentLocation);
        employeeRepository.save(employee1);
        employee1.setCurrentLocation(new Location(11, 654, null, null, "test"));

        Employee employee2 = employeeRepository.getOne(employeeId);
        assertEquals(employee1.getCurrentLocation().getDetails(), employee2.getCurrentLocation().getDetails());

    }

	// Anfragen mit curl testen
    // curl -w "\n" localhost:8080/students/34622/

    // curl -X POST -w "\n" localhost:8080/students/34622/courses/
}
