package com.example.jpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpahibernate.JpaHibernateApplication;
import com.example.jpahibernate.entity.Course;
import com.example.jpahibernate.entity.Student;

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
		//Results -> [Course [name=Physics], Course [name=Computer Science], Course [name=Math]]
		return;
	}
	
	@Test
	public void jpql_students_with_passport_in_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
		//Results -> [Student [name=Sourav], Student [name=Chirantan]]
		return;
	}
	
	//like
	//Between 100 and 1000
	//is null
	//upper, lower, trim, length
	
	//join => select c, s from Course c JOIN c.students s
	//left join => select c, s from Course c LEFT JOIN c.students s
	//cross join -> select c, s from Course c , Student s
	
	@Test
	public void join() {
		Query query = em.createQuery("select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result: resultList) {
			logger.info("Course -> {}, Student -> {}", result[0], result[1]);
		}
	}
	
	@Test
	public void left_join() {
		Query query = em.createQuery("select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result: resultList) {
			logger.info("Course -> {}, Student -> {}", result[0], result[1]);
		}
	}
	
	@Test
	public void cross_join() {
		Query query = em.createQuery("select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result: resultList) {
			logger.info("Course -> {}, Student -> {}", result[0], result[1]);
		}
	}
}
