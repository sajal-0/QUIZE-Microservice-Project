package com.example.quiz_service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizService1Application {

	public static void main(String[] args) {
		SpringApplication.run(QuizService1Application.class, args);
		System.err.println("started ... Quiz !");
	}

}
