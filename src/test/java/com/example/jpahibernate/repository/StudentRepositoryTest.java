package com.example.jpahibernate.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpahibernate.JpaHibernateApplication;
import com.example.jpahibernate.entity.Passport;
import com.example.jpahibernate.entity.Student;

@SpringBootTest(classes = JpaHibernateApplication.class)
public class StudentRepositoryTest {
Logger logger = LoggerFactory.getLogger(StudentRepositoryTest.class);
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("Passport -> {}", student.getPassport());
	}
	
	@Test
	@Transactional
	public void retrievePassportAndAssociateStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("Passport -> {}", passport);
		logger.info("Student -> {}", passport.getStudent());
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);
		logger.info("Student -> {}", student);
		logger.info("Courses -> {}", student.getCourses());
	}

}
