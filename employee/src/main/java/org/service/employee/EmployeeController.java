package org.service.employee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@RequestMapping(method = RequestMethod.GET)
	public String getEmployees() {
		System.out.println("Get Request Received...");
		return "Hello Karthik";
	}
}
