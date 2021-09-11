package com.example.jpahibernate.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.example.jpahibernate.JpaHibernateApplication;
import com.example.jpahibernate.entity.Course;

@SpringBootTest(classes = JpaHibernateApplication.class)
public class CourseSpringDataRepositoryTest {
	Logger logger = LoggerFactory.getLogger(CourseSpringDataRepositoryTest.class);
	
	@Autowired
	CourseSpringDataRepository repository; 
	
	@Test
	public void findById_CoursePresent() {
		Optional<Course> courseOptional = repository.findById(10001L);	
		assertTrue(courseOptional.isPresent());
	}
	
	@Test
	public void findById_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		assertFalse(courseOptional.isPresent());
	}
	
	@Test
	public void playingAroundWithSpringDataRepository() {
//		Course course = new Course("Microservices in 100 Steps");
//		repository.save(course);
//		course.setName("Microservices in 100 Steps - Updated");
//		repository.save(course);
		logger.info("Course -> {} ", repository.findAll());
		logger.info("Count -> {} ", repository.count());
	}
}
