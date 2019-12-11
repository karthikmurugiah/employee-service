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
	private EmployeeService employeeService;
		
	@RequestMapping(method = RequestMethod.POST)
	public Employee registerEmployee(@RequestBody Employee employee) {
		//TODO: Add logging
		return employeeService.registerEmployee(employee);
	}
	
	@RequestMapping(value="all", method = RequestMethod.GET)
	public List<Employee> listAllEmployees() {
		//TODO: Add Logging and pagenation
		return employeeService.listAllEmployees();
	}
}
