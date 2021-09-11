package com.example.jpahibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpahibernate.entity.Course;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{
	
	// find == retrieve == query same function
	List<Course> findByNameAndId(String name, Long id);
	List<Course> countByName(String name);
	List<Course> findByName(String name);
	List<Course> findByNameOrderByIdDesc(String name);
	List<Course> deleteByName(String name);
	
	@Query("Select c from Course c where name like '%Computer Science%'")
	List<Course> courseWithComputerScienceInName();

	@Query(value = "Select * from Course c where name like '%Computer Science%'",
			nativeQuery=true)
	List<Course> courseWithComputerScienceInNameUsingNativeQuery();

	@Query(name = "query_get_all_computer_science_course")
	List<Course> courseWithComputerScienceInNameUsingNamedQuery();
	
}
