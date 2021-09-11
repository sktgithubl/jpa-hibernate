package com.example.jpahibernate.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpahibernate.JpaHibernateApplication;
import com.example.jpahibernate.entity.Course;

@SpringBootTest(classes = JpaHibernateApplication.class)
public class CourseRepositoryTest {
	Logger logger = LoggerFactory.getLogger(CourseRepositoryTest.class);
	
	@Autowired
	CourseRepository courseRepository; 
	
	@Test
	public void findById_basic() {
		logger.info("findById test executing");
		Course course = courseRepository.findById(10001L);
		assertEquals("Physics", course.getName());
	}
	
	@Test
	@Transactional
	public void findById_firstLevelCacheDemo() {
		logger.info("findById test executing");
		Course course = courseRepository.findById(10001L);
		logger.info("First Course Retrieved {}", course);
		Course course1 = courseRepository.findById(10001L);
		logger.info("First Course Retrieved again {}", course);
		assertEquals("Physics", course.getName());
		assertEquals("Physics", course1.getName());
	}
	
	@Test
	@DirtiesContext
	public void deleteById_basic() {
		logger.info("deleteById execution start");
		courseRepository.deleteById(10002L);
		assertNull(courseRepository.findById(10002L));
		logger.info("deleteById execution stop");
	}
	
	@Test
	@DirtiesContext
	public void save_basic() {
		Course course = courseRepository.findById(10001L);
		assertEquals("Physics", course.getName());
		
		course.setName("Physics - updated");
		courseRepository.save(course);
		
		Course course1 = courseRepository.findById(10001L);
		assertEquals("Physics - updated", course1.getName());
	}
	
	@Test
	@Transactional
	public void retrieveReviewsByCourseId() {
		Course course = courseRepository.findById(10003L);
		logger.info("{}", course.getReviews());
	}
}
