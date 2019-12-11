package org.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee registerEmployee(Employee employee){
		return employeeRepository.save(employee);
	}
	
	public List<Employee> listAllEmployees(){
		return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
	}
}
