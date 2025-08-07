package com.telusko.question_service1.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.question_service1.Dao.QuestionDao;
import com.telusko.question_service1.Entity.QuesitonWrpper;
import com.telusko.question_service1.Entity.Questions;
import com.telusko.question_service1.Entity.Response;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao queDao;

	public ResponseEntity<List<Questions>> getAllQuestion() {
		try {
			List<Questions> questions = queDao.findAll();
			return new ResponseEntity<>(questions, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Questions>> findByCategory(String category) {
		try {
			List<Questions> questions = queDao.findByCategory(category);
			if (questions.isEmpty()) {
				return new ResponseEntity<>(questions, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(questions, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> addQuestions(Questions questions) {
		try {
			queDao.save(questions);
			return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to add question", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> updateQuestion(Integer id, Questions que) {
		Optional<Questions> question = queDao.findById(id);

		if (question.isPresent()) {
			Questions ques = question.get();
			ques.setQuestionTitle(que.getQuestionTitle());
			ques.setCategory(que.getCategory());
			ques.setDifficultyLevel(que.getDifficultyLevel());
			ques.setOption1(que.getOption1());
			ques.setOption2(que.getOption2());
			ques.setOption3(que.getOption3());
			ques.setOption4(que.getOption4());
			ques.setRightAns(que.getRightAns());

			queDao.save(ques);
			return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> deleteQuestion(Integer id) {
		try {
			if (queDao.existsById(id)) {
				queDao.deleteById(id);
				return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to delete question", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<QuesitonWrpper>> getQuestions(List<Integer> questionIds) {
		List<QuesitonWrpper> wrappers = new ArrayList<>();
		List<Questions> questions = new ArrayList<>();

		for (Integer id : questionIds) {
			questions.add(queDao.findById(id).get());
		}

		for (Questions que : questions) {
			QuesitonWrpper wrapper = new QuesitonWrpper();
			wrapper.setId(que.getId());
			wrapper.setQuestionTitle(que.getQuestionTitle());
			wrapper.setOption1(que.getOption1());
			wrapper.setOption2(que.getOption2());
			wrapper.setOption3(que.getOption3());
			wrapper.setOption4(que.getOption4());
			wrappers.add(wrapper);
		}

		return new ResponseEntity<>(wrappers, HttpStatus.OK);
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuize(String categoryName, Integer numQuestions) {
		List<Integer> questions = queDao.findRandomQuestionByCategory(categoryName, numQuestions);

		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {

		int right = 0;

		for (Response response : responses) {
			Questions question = queDao.findById(response.getId()).get();
			if (response.getResponse().equals(question.getRightAns())) {
				right++;

			}
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

}
