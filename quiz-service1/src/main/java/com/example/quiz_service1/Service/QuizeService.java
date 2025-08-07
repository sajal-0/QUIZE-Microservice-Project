package com.example.quiz_service1.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quiz_service1.Dao.QuizeDao;
import com.example.quiz_service1.Entity.QuesitonWrpper;

import com.example.quiz_service1.Entity.Quize;
import com.example.quiz_service1.Entity.Response;
import com.example.quiz_service1.Feign.QuizInterface;

@Service
public class QuizeService {
 
	@Autowired
	QuizeDao quizeDao;
	

	@Autowired
	QuizInterface quizeInterface;

	public ResponseEntity<String> createQuize(String category, int numQ, String title) {
		
		List<Integer> questions = quizeInterface.getQuestionsForQuiz(category,numQ).getBody();
	
		Quize quiz = new Quize();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		
		quizeDao.save(quiz);
		
		return new ResponseEntity<>("Sucess",HttpStatus.CREATED);
	}


	public ResponseEntity<List<QuesitonWrpper>> getQuizeQuestions(Integer id) {
		Quize quize = quizeDao.findById(id).get();
		
		List<Integer> questionIds = quize.getQuestions();
		ResponseEntity<List<QuesitonWrpper>> list =  quizeInterface.getQuestions(questionIds);
		
		return list;
		}


	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		
		ResponseEntity<Integer>  score=quizeInterface.getScore(responses);
		return score;
	}



}
