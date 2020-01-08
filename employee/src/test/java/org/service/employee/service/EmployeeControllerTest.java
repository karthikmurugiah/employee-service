package org.service.employee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dreamworks.employee.service.Employee;
import com.dreamworks.employee.service.EmployeeController;
import com.dreamworks.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	@Mock
	private EmployeeService empoyeeService;

	@InjectMocks
	private EmployeeController employeeController;

	@Autowired
	private MockMvc mockMvc;

	private JacksonTester<Employee> employeeJson;

	@Before
	public void setup() {
		// We would need this line if we would not use MockitoJUnitRunner
		// MockitoAnnotations.initMocks(this);
		// Initializes the JacksonTester
		JacksonTester.initFields(this, new ObjectMapper());
		// MockMvc standalone approach
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	public void canRetrieveWhenExists() throws Exception {

		List<Employee> list = new ArrayList<>();
		list.add(new Employee("firstName1", "lastName1", "male", "1981-01-01", "engineering"));
		list.add(new Employee("firstName2", "lastName2", "male", "1981-01-02", "engineering"));
		list.add(new Employee("firstName3", "lastName3", "female", "1981-01-03", "engineering"));

		// given
		given(empoyeeService.listAllEmployees()).willReturn(list);

		// when
		MockHttpServletResponse response = mockMvc.perform(get("/employees").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isNotEmpty();
	}

	@Test
	public void canRetrieveWhenExists1() throws Exception {

		List<Employee> list = new ArrayList<>();
		list.add(new Employee("firstName1", "lastName1", "male", "1981-01-01", "engineering"));
		list.add(new Employee("firstName2", "lastName2", "male", "1981-01-02", "engineering"));
		list.add(new Employee("firstName3", "lastName3", "female", "1981-01-03", "engineering"));

		// given
		given(empoyeeService.listAllEmployees()).willReturn(list);

		List<Employee> response = employeeController.listAllEmployees();

		// then
		assertThat(response.get(0).getFirstName()).isEqualTo("firstName1");
	}
	
	@Test
	public void cannotRetrieveWhenNotExist() throws Exception {

		// given
		given(empoyeeService.listAllEmployees()).willReturn(Collections.<Employee>emptyList());

		// when
		MockHttpServletResponse response = mockMvc.perform(get("/employees").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo("[]");
	}

	@Test
	public void canCreateANewEmployee() throws Exception {
		// when
		MockHttpServletResponse response = mockMvc
				.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeJson
						.write(new Employee("firstName1", "lastName1", "male", "1981-01-01", "engineering")).getJson()))
				.andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	public void testInvalidName() throws Exception {
		// when
		MockHttpServletResponse response = mockMvc
				.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeJson
						.write(new Employee("", "lastName1", "male", "1981-01-01", "engineering")).getJson()))
				.andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	//@Test // Cannot test this since this is custom validation
	public void testInvalidGender() throws Exception {
		// when
		MockHttpServletResponse response = mockMvc
				.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeJson
						.write(new Employee("firstName1", "lastName1", "INVALID", "1981-01-01", "engineering")).getJson()))
				.andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
}
