package com.dev.forest.quiz.model.service;

import java.util.List;

import com.dev.forest.quiz.model.dto.QuizDTO;

import jakarta.validation.constraints.Min;

public interface QuizService {

	void insertQuiz(QuizDTO quiz);

	List<QuizDTO> findAll();

	QuizDTO findById(Long quizNo);

	List<QuizDTO> findByCategory(String category);

	List<QuizDTO> findByRandomNo();


}
