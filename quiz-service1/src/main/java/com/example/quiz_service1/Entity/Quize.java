package com.example.quiz_service1.Entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Quize {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	
	@ElementCollection
	private List<Integer> questionIds;

	
	public Quize() {
		super();
	}


	public Quize(Integer id, String title, List<Integer> questionIds) {
		super();
		this.id = id;
		this.title = title;
		this.questionIds = questionIds;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public List<Integer> getQuestions() {
		return questionIds;
	}


	public void setQuestions(List<Integer> questions) {
		this.questionIds = questions;
	}
	
	
	
}
