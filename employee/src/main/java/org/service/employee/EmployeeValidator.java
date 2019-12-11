package org.service.employee;

public class EmployeeValidator {
	public void validate(Employee employee) throws Exception{
		isValidGender(employee.getGender());
	}
	
	public void isValidGender(String gender) throws Exception{
		if(!(gender.equalsIgnoreCase("MALE") || gender.equalsIgnoreCase("FEMALE"))){
			//log here
			throw new Exception("Enter a valid gender!");
		}
	}
	
}
