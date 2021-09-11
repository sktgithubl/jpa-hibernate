package com.example.jpahibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpahibernate.entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{

}
