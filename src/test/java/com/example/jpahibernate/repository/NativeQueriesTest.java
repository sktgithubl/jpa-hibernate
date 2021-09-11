package com.example.jpahibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.jpahibernate.JpaHibernateApplication;
import com.example.jpahibernate.entity.Course;

@SpringBootTest(classes = JpaHibernateApplication.class)
public class NativeQueriesTest {
	
	Logger logger = LoggerFactory.getLogger(NativeQueriesTest.class);
	
	@Autowired
	EntityManager em;
	
	@Test
	public void native_basic() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List resultList = query.getResultList();
		logger.info("Native Query: SELECT * FROM COURSE -> {}", resultList);
		return;
	}
	
	@Test
	public void native_basic_with_parameters() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id = ?", Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		logger.info("Native Query with parameters: SELECT * FROM COURSE WHERE id = ? -> {}", resultList);
		return;
	}
	
	@Test
	public void native_basic_with_named_parameters() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id = :id", Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		logger.info("Native Query with named parameters: SELECT * FROM COURSE WHERE id = :id -> {}", resultList);
		return;
	}
	
	@Test
	@Transactional
	public void native_queries_to_update() {
		Query query = em.createNativeQuery("UPDATE COURSE set last_updated_date=sysdate()");
		int noOfRowUpdated = query.executeUpdate();
		logger.info("noOfRowUpdated -> {}", noOfRowUpdated);
		return;
	}
}
