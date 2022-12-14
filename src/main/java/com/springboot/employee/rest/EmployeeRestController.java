package com.springboot.employee.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public EmployeeRestController(EmployeeService theEmployeeService)
	{
		employeeService = theEmployeeService;
	}
	
	@RequestMapping("/")
	public String Index() 
	{		
		return "Employee API is Up and Running !!";		
	}
	
	
	@GetMapping("/employees")
	public List<Employee> findAll()
	{		
		return employeeService.findAll();
	}
	
	
	// add mapping for GET /employees/{employeeId}
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId)
	{
		
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null)
		{
		   throw new RuntimeException("Employee id not found - "+ employeeId);	
		}
		
		return theEmployee;
	}
	
	// add mapping for POST / Employees - add new Employee
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee)
	{
		
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee)
	{
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId)
	{
		Employee tempEmployee = employeeService.findById(employeeId);
		
		// throw exception if null
		
		if(tempEmployee == null)
		{
			throw new RuntimeException("Employee id not found - "+ employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return "Deleted Employee Id "+ employeeId;
	}
	
	
	
}
