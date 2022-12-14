package com.springboot.employee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.employee.entity.Employee;


@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager)
	{
		entityManager = theEntityManager;		
	}
	
	
	@Override
	public List<Employee> findAll() {
			
		// create a query		
		Query theQuery = 
				entityManager.createQuery("from Employee");
				
		// execute the query to get results		
		List<Employee> employees = theQuery.getResultList();
		
		//return the results
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		
		// get employee
		
		Employee theEmployee = 
				entityManager.find(Employee.class, theId);
						
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		
		// save or update the employee
		Employee dbEmployee = entityManager.merge(theEmployee);
		
		// update with id from db...
		theEmployee.setId(dbEmployee.getId());

	}

	@Override
	public void deleteById(int theId) {
		
		// delete by id
		
		Query theQuery = entityManager.createQuery("delete from Employee where id=: employeeId");
		
		theQuery.setParameter("employeeId",theId);
		
		theQuery.executeUpdate();

	}

}
