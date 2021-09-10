package com.example.jpahibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpahibernate.entity.Review;
import com.example.jpahibernate.repository.CourseRepository;
import com.example.jpahibernate.repository.StudentRepository;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {
	
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;
	
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
		studentRepository.insertCourseIntoStudent();
	}

}
