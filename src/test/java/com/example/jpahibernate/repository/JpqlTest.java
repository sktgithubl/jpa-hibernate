package com.example.jpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpahibernate.JpaHibernateApplication;
import com.example.jpahibernate.entity.Course;

@SpringBootTest(classes=JpaHibernateApplication.class)
public class JpqlTest {
	
	Logger logger = LoggerFactory.getLogger(JpqlTest.class);
	
	@Autowired
	EntityManager em;
	
	@Test
	public void jpql_basic() {
		List resultList = em.createQuery("Select c from Course c").getResultList();
		logger.info("jpql Query: select c form Course c -> {}", resultList);
		return;
	}
	
	@Test
	public void namedQuery_basic() {
		List resultList = em.createNamedQuery("query_get_all_courses").getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_typed() {
		TypedQuery<Course> typedQuery = em.createQuery("select c from Course c", Course.class);
		List<Course> resultList = typedQuery.getResultList();
		logger.info("Select c fromm Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		return;
	}
	
	@Test
	public void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		//Results -> [Course [name=Physics]]
		return;
	}
	
	@Test
	public void jpql_courses_with_ordered_by_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students)", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		//Results -> [Course [name=Math], Course [name=Computer Science], Course [name=Physics]]
		return;
	}
	
	@Test
	public void jpql_courses_with_ordered_by_desc_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		//Results -> [Course [name=Math], Course [name=Computer Science], Course [name=Physics]]
		return;
	}
}
