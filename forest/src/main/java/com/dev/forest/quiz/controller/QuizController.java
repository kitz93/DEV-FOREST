package com.dev.forest.quiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.forest.quiz.model.dto.QuizDTO;
import com.dev.forest.quiz.model.service.QuizService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("quizs")
@Slf4j
public class QuizController {
	
	private final QuizService quizservice;
	
	@PostMapping
	public ResponseEntity<?> insertQuiz(QuizDTO quiz) {

		log.info("퀴즈 입력값 : {}", quiz);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("퀴즈 등록 성공");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
