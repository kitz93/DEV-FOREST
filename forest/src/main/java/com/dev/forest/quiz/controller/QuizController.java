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
	
	@GetMapping("/category")
	public ResponseEntity<List<QuizDTO>> findByCategory(@RequestParam (name="category") String category) {
		List<QuizDTO> quizList = quizService.findByCategory(category);
		return ResponseEntity.ok(quizList);
	}
	
	@GetMapping("/random")
	public ResponseEntity<List<QuizDTO>> findByRandomNo() {
		List<QuizDTO> quizList = quizService.findByRandomNo();
		return ResponseEntity.ok(quizList);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
