package com.example.jpahibernate.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.jpahibernate.entity.Course;
import com.example.jpahibernate.entity.Passport;
import com.example.jpahibernate.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	
	private Logger logger = LoggerFactory.getLogger(StudentRepository.class);
	
	@Autowired
	EntityManager em;
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	public Student save(Student student) {
		if(student.getId() == null) {
			logger.info("inserting");
			em.persist(student);
		} else {
			logger.info("updating");
			em.merge(student);
		}
		
		return student;
	}
	
	public Student saveStudentWithPassword() {
		Passport passport = new Passport("X1234");
		em.persist(passport);
		Student student = new Student("Asima");
		student.setPassport(passport);
		em.persist(student);
		return student;
	}
	
	public void playWithEntityManager() {
		Student student1 = new Student("React JS");
		student1.setName("React JS - updated");
		em.persist(student1);
		em.flush();
		Student student2 = findById(10001L);
		student2.setName("Angular - updated");
	}
	
	public void insertHardCodedStudentAndCourses() {
		Student student = new  Student("Jack");
		Course course = new Course("Microservice in 100 steps");
		
		student.addCourse(course);
		course.addStudent(student);
		
		em.persist(student);
		em.persist(course);
	}
	
	public void insertCourseIntoStudent() {
		Student student = findById(20003L);
		logger.info("student -> {}", student);
		Course course = em.find(Course.class, 10003L);
		student.addCourse(course);
		em.persist(course);
	}
}
