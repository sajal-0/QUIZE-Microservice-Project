package com.example.quiz_service1.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_service1.Entity.QuesitonWrpper;
import com.example.quiz_service1.Entity.QuizDto;
import com.example.quiz_service1.Entity.Response;
import com.example.quiz_service1.Service.QuizeService;

@RestController
@RequestMapping("/quize")
public class QuizeController {
	
	@Autowired
	QuizeService quizeSer;
	
	
	@GetMapping("/create")
	public ResponseEntity<String> createQuize(@RequestBody QuizDto quizDto){
		return quizeSer.createQuize(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuesitonWrpper>> getQuizeQuestions(@PathVariable Integer id){
		return quizeSer.getQuizeQuestions(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuize(@PathVariable Integer id, @RequestBody List<Response> responses){
		return quizeSer.calculateResult(id, responses);
	}
}
