package com.dreamworks.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dreamworks.employee.dao.EmployeeRepository;
import com.dreamworks.employee.exception.ValidationException;
import com.dreamworks.employee.validator.EmployeeValidator;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	EmployeeValidator validator;
	
	public Employee registerEmployee(Employee employee) throws ValidationException{
		validator.validate(employee);
		return employeeRepository.save(employee);
	}
	
	public List<Employee> listAllEmployees(){
		return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
	}
}
