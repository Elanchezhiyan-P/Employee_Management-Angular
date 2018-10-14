package com.ksr.org.employeeserver.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ksr.org.employeeserver.dto.Employee;
import com.ksr.org.employeeserver.repository.EmployeeRepo;

@RestController
public class EmployeeController {
	
	
	private EmployeeRepo employeeRepo;
	
	
	@Autowired
	public EmployeeController(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	
	@RequestMapping(value="/api/register", method=RequestMethod.POST)
	public Object registerUser(@RequestBody Employee employee) {
		System.out.println(employee.getName());
		Employee savedEmployee = employeeRepo.save(employee);
		HashMap<String, String> resultData = new HashMap();
		if(savedEmployee.getId()> 0) {
			resultData.put("status", "success");
			return resultData;
		}
		resultData.put("status", "failed");
		return resultData;
	}
	
	@RequestMapping(value="/api/login", method=RequestMethod.POST)
	public Object isValidUser(@RequestBody Employee employee) {
		Employee savedEmployee = employeeRepo.findByEmail(employee.getEmail());
		HashMap<String, String> resultData = new HashMap();
		if(savedEmployee.getId()> 0) {
			if(savedEmployee.getPassword().equals(employee.getPassword())) {
				resultData.put("status", "success");
				return resultData;
			}
			
		}
		resultData.put("status", "failed");
		return resultData;
		
	}
	
	@RequestMapping(value="/api/get-employees", method=RequestMethod.GET)
	public ArrayList<Employee> getAllEmployees() {
		return (ArrayList<Employee>) employeeRepo.findAll();
	}


}
