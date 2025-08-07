package com.example.quiz_service1.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quiz_service1.Entity.QuesitonWrpper;
import com.example.quiz_service1.Entity.Response;



@FeignClient("QUESTION-SERVICE1")
public interface QuizInterface {
	
	@PostMapping("/question/getQuestions")
	public ResponseEntity<List<QuesitonWrpper>> getQuestions(@RequestBody List<Integer> questionIds);
		
	
	@PostMapping("/question/getscore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response);
	
	
	@GetMapping("/question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName ,@RequestParam Integer numQuestions);
	
}
