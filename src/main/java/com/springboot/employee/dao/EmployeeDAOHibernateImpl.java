package com.springboot.employee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.employee.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define field for entitymanager
	private EntityManager entityManager;
	
	// setup constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager)
	{
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		
		// get current hibernate session
		org.hibernate.Session currentSession = entityManager.unwrap(org.hibernate.Session.class);
						
		// create query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		// execute query and get result list		
		List<Employee> employees = theQuery.getResultList();
		
		
		// return the results		
		return employees;
				
	}

	@Override
	public Employee findById(int theId) {
		// get current hibernate session
		org.hibernate.Session currentSession = entityManager.unwrap(org.hibernate.Session.class);
		
		// get the employee
		Employee theEmployee = currentSession.get(Employee.class,theId);
				
		// return the employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		// TODO Auto-generated method stub
		
		org.hibernate.Session currentSession = entityManager.unwrap(org.hibernate.Session.class);
		
		currentSession.saveOrUpdate(theEmployee);
				
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		
		org.hibernate.Session currentSession = entityManager.unwrap(org.hibernate.Session.class);
		
		Query theQuery = 
				currentSession.createQuery("delete from Employee where id := employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
				
		
	}

}
