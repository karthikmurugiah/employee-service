package com.dreamworks.employee.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dreamworks.employee.service.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
