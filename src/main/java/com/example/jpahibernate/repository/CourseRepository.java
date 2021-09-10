package com.example.jpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.jpahibernate.entity.Course;
import com.example.jpahibernate.entity.Review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Transactional
public class CourseRepository {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(CourseRepository.class);
	
	@Autowired
	EntityManager em;
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	public Course save(Course course) {
		if(course.getId() == null) {
			logger.info("inserting");
			em.persist(course);
		} else {
			logger.info("updating");
			em.merge(course);
		}
		
		return course;
	}
	
	public void playWithEntityManager() {
		Course course1 = new Course("React JS");
		course1.setName("React JS - updated");
		em.persist(course1);
		em.flush();
		Course course2 = findById(10001L);
		course2.setName("Angular - updated");
	}
	
	public void addHardCodedReviewsForCourse() {
		Course course = new Course("React JS");
		logger.info("course.getReviews() -> {}", course.getReviews());
		
		Review review1 = new Review("5", "Great hands-on Stuff");
		Review review2 = new Review("5", "Hatsoff");
		
		course.addReview(review1);
		review1.setCourse(course);
		
		course.addReview(review2);
		review2.setCourse(course);
		
		em.persist(review1);
		em.persist(review2);
	}
	
	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		Course course = findById(courseId);
		logger.info("course -> {}", course);
		
		for(Review review: reviews) {
			course.addReview(review);;
			review.setCourse(course);
			em.persist(review);
		}
	}
}
