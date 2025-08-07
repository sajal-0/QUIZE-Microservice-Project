package com.telusko.question_service1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.telusko.question_service1.Entity.QuesitonWrpper;
import com.telusko.question_service1.Entity.Questions;
import com.telusko.question_service1.Entity.Response;
import com.telusko.question_service1.Service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestioinController {

	@Autowired
	QuestionService queService;
	
	@GetMapping("/allquestions")
	public ResponseEntity<List<Questions>> getAllQuestion() {
		return queService.getAllQuestion();
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Questions>> findByCategory(@PathVariable String category){
		return queService.findByCategory(category);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestions(@RequestBody Questions questions) {
		return queService.addQuestions(questions);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateQuestion(@PathVariable Integer id,@RequestBody Questions que) {
		return 		queService.updateQuestion(id,que);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteQuestion(@PathVariable Integer id) {
		 queService.deleteQuestion(id);
	}
	
	
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuesitonWrpper>> getQuestions(@RequestBody List<Integer> questionIds){
		return queService.getQuestions(questionIds);
	}
	
	
	@PostMapping("/getscore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> response){
		return queService.getScore(response);
	}
	
	
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName ,@RequestParam Integer numQuestions){
		return queService.getQuestionsForQuize(categoryName,numQuestions);
	}
	
	
	
	
	
	
	
	
	
	
}
