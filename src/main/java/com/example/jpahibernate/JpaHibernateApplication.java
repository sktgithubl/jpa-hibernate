package com.example.jpahibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpahibernate.entity.FullTimeEmployee;
import com.example.jpahibernate.entity.PartTimeEmployee;
import com.example.jpahibernate.entity.Review;
import com.example.jpahibernate.repository.CourseRepository;
import com.example.jpahibernate.repository.EmployeeRepository;
import com.example.jpahibernate.repository.StudentRepository;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Review> reviews = new ArrayList<>( Arrays.asList(
				new Review("1", "Bad"),
				new Review("2", "veryBad")));
//		courseRepository.playWithEntityManager();
//		studentRepository.saveStudentWithPassword();
//		courseRepository.addHardCodedReviewsForCourse();
//		courseRepository.addReviewsForCourse(10003L, reviews);
//		studentRepository.insertHardCodedStudentAndCourses();
//		studentRepository.insertCourseIntoStudent();
//		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
//		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));
//		logger.info("Full time Employees -> {}", employeeRepository.retrieveAllFullTimeEmployees());
//		logger.info("Part Time Employees -> {}", employeeRepository.retrieveAllPartTimeEmployees());
		
	}

}
