package com.example.jpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.jpahibernate.entity.Course;
import com.example.jpahibernate.entity.Employee;
import com.example.jpahibernate.entity.FullTimeEmployee;
import com.example.jpahibernate.entity.PartTimeEmployee;
import com.example.jpahibernate.entity.Review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Transactional
public class EmployeeRepository {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(EmployeeRepository.class);
	
	@Autowired
	EntityManager em;
	
	public void insert(Employee employee) {
		em.persist(employee);
	}
	
	public List<PartTimeEmployee> retrieveAllPartTimeEmployees() {
		return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
	}
	
	public List<FullTimeEmployee> retrieveAllFullTimeEmployees() {
		return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}
	
}
