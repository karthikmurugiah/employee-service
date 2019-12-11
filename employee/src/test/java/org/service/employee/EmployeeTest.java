package org.service.employee;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.junit4.SpringRunner;

@JsonTest
@RunWith(SpringRunner.class)
public class EmployeeTest {

	private static Validator validator = null;

	@BeforeClass
	public static void setupValidatorInstance() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	public void whenNotNull() {
		Employee employee = new Employee(null, null, null, null, null);
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		assertThat(violations.size()).isEqualTo(5);
	}
	
	@Test
	public void whenFirstNameNull() {
		Employee employee = new Employee(null, "lastName", "male", "01/01/1900", "engineering");
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		assertThat(violations.size()).isEqualTo(1);
	}
	
	@Test
	public void whenLastNameNull() {
		Employee employee = new Employee("firstName", null, "male", "01/01/1900", "engineering");
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		assertThat(violations.size()).isEqualTo(1);
	}

	@Test
	public void whenDateNull() {
		Employee employee = new Employee("firstName", "lastName", "male", null, "engineering");
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		assertThat(violations.size()).isEqualTo(1);
	}

	@Test
	public void whenDepartmentNull() {
		Employee employee = new Employee("firstName", "lastName", "male", "01/01/1900", null);
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		assertThat(violations.size()).isEqualTo(1);
	}
	@Test
	public void whenGenderNull() {
		Employee employee = new Employee("firstName", "lastName", null, "01/01/1900", "engineering");
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		assertThat(violations.size()).isEqualTo(1);
	}
	
	@Test
	public void whenAllWell() {
		Employee employee = new Employee("firstName", "lastName", "male", "01/01/1900", "engineering");
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		assertThat(violations.size()).isEqualTo(0);
	}

}
