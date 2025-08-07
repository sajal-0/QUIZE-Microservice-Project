package com.telusko.question_service1.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telusko.question_service1.Entity.Questions;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer>{
	

	List<Questions> findByCategory(String category);
	

	 @Query(value = "SELECT q.id FROM questions q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	    List<Integer> findRandomQuestionByCategory(@Param("category") String category, @Param("numQ") int numQ);


}
