package com.dreamworks.employee.service;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dreamworks.employee.exception.CustomExceptionHandler;
import com.dreamworks.employee.exception.ValidationException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;
		
	@RequestMapping(method = RequestMethod.POST)
	public Employee registerEmployee(@Valid @RequestBody Employee employee) throws ValidationException {
		logger.trace("Start registration");
		return employeeService.registerEmployee(employee);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Employee> listAllEmployees() {
		logger.trace("Start list employees");
		return employeeService.listAllEmployees();
	}
}
