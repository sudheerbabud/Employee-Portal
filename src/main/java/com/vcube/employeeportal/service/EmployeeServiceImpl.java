package com.vcube.employeeportal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.employeeportal.dto.EmployeeDto;
import com.vcube.employeeportal.mapper.EmployeeMapper;
import com.vcube.employeeportal.model.Employee;
import com.vcube.employeeportal.repo.EmployeeRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		
		List<EmployeeDto> employeeDtos = employees.stream()
										.map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
										.collect(Collectors.toList());
		return employeeDtos;
	}
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}
	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + employeeId));
		EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
		return employeeDto;
	}
	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
		Employee existingEmployee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + employeeId));
		existingEmployee.setFirstName(employeeDto.getFirstName());
		existingEmployee.setLastName(employeeDto.getLastName());
		existingEmployee.setEmail(employeeDto.getEmail());
		employeeRepository.save(existingEmployee);
		return EmployeeMapper.mapToEmployeeDto(existingEmployee);
	}
	@Override
	public void deleteEmployee(Long employeeId) {
		Employee existingEmployee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + employeeId));
		employeeRepository.deleteById(employeeId);
	}
}
