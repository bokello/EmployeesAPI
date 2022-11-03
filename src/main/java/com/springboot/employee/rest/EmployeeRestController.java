package com.springboot.employee.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.dao.EmployeeDAO;
import com.springboot.employee.entity.Employee;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	/*
	 * inject employee DAO
	 * expose "/employees returns the list of employees
	 * 
	 */
		
	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeRestController(EmployeeDAO theEmployeeDAO)
	{
		employeeDAO = theEmployeeDAO;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll()
	{		
		return employeeDAO.findAll();
	}
	
}
