package com.dev.forest.quiz.model.service;

import java.util.List;
import java.util.Map;

import com.dev.forest.quiz.model.dto.QuizDTO;

import jakarta.validation.constraints.Min;

public interface QuizService {

	List<QuizDTO> findByCategory(String category);

	List<QuizDTO> findByRandomNo();


}
