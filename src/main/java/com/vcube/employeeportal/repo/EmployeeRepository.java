package com.vcube.employeeportal.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vcube.employeeportal.model.Employee;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
