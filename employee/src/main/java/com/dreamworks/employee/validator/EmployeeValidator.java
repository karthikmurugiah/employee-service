package com.dreamworks.employee.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import com.dreamworks.employee.exception.ValidationException;
import com.dreamworks.employee.service.Employee;

@Component
public class EmployeeValidator {
	public void validate(Employee employee) throws ValidationException {
		isValidGender(employee.getGender());
		isValidDate(employee.getDateOfBirth());
	}

	public void isValidGender(String gender) throws ValidationException {
		if (!(gender.equalsIgnoreCase("MALE") || gender.equalsIgnoreCase("FEMALE"))) {
			throw new ValidationException("Enter a valid gender!");
		}
	}

	public void isValidDate(String date) throws ValidationException {
		try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
        	throw new ValidationException("Enter a valid date!");
        }
	}

}
