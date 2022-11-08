package com.springboot.employee.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.entity.Employee;
import com.springboot.employee.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	/*
	 * inject employee DAO
	 * expose "/employees returns the list of employees
	 * 
	 */
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		
		employeeService = theEmployeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll()
	{		
		return employeeService.findAll();
	}
	
}
