package com.example.jpahibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Course")
@NamedQueries(value = {
		@NamedQuery(name = "query_get_all_courses",
				query = "select c from Course c")
})
public class Course {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@OneToMany(mappedBy = "course", fetch=FetchType.EAGER)
	private List<Review> reviews = new ArrayList<>();
	
	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<>();
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	@CreationTimestamp
	private LocalDateTime createdDate;

	public Course() {
		super();
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	
	public void addStudent(Student student) {
		students.add(student);
	}
	
	public void removeStudent(Student student) {
		students.remove(student);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public List<Student> getStudents() {
		return students;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}
}
