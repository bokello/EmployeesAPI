package com.springboot.employee.dao;

import java.util.List;

import com.springboot.employee.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
		
}
