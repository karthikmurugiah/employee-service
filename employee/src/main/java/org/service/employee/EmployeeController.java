package org.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getEmployees() {
		System.out.println("Get Request Received...");
		return "Hello Karthik";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Employee addNewUsers(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@RequestMapping(value="all", method = RequestMethod.GET)
	public List<Employee> listAllEmployees() {
		return employeeRepository.findAll();
	}
}
