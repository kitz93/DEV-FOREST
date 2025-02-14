package com.dev.forest.quiz.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.forest.quiz.model.dto.QuizDTO;
import com.dev.forest.quiz.model.service.QuizService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("quizs")
@Slf4j
public class QuizController {
	
	private final QuizService quizService;
	
	@PostMapping
	public ResponseEntity<?> insertQuiz(@RequestBody @Valid QuizDTO quiz) {
		log.info("퀴즈 입력값 : {}", quiz);
		return ResponseEntity.status(HttpStatus.CREATED).body("퀴즈 등록 성공");
	}
	
	@GetMapping
	public ResponseEntity<List<QuizDTO>> findAll() {
		List<QuizDTO> quizDTO = quizService.findAll();
		return ResponseEntity.ok(quizDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<QuizDTO> findById(@PathVariable(name="id") @Min(value=1, message="0보다 작은 수 입니다.") Long quizNo) {
		// log.info("퀴즈 정보 : {}", quizNo);
		QuizDTO quiz = quizService.findById(quizNo);
		return ResponseEntity.ok(quiz);
	}
	
	@GetMapping("/category")
	public ResponseEntity<List<QuizDTO>> findByCategory(@RequestParam (name="category") String category) {
		// log.info("카테고리 정보 : {}", category);
		List<QuizDTO> quizList = quizService.findByCategory(category);
		return ResponseEntity.ok(quizList);
	}
	
	@GetMapping("/random")
	public ResponseEntity<List<QuizDTO>> findByRandomNo() {
		List<QuizDTO> quizList = quizService.findByRandomNo();
		return ResponseEntity.ok(quizList);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
