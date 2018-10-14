package com.ksr.org.employeeserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ksr.org.employeeserver.dto.Employee;

@Service
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {
	
	Employee findByEmail(String email);
	

}
