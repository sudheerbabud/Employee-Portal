package com.vcube.employeeportal.service;

import java.util.List;

import com.vcube.employeeportal.dto.EmployeeDto;

public interface EmployeeService {
	List<EmployeeDto> getAllEmployees();

	EmployeeDto createEmployee(EmployeeDto employee);

	EmployeeDto getEmployeeById(Long employeeId);

	EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);

	void deleteEmployee(Long employeeId);
}
