package com.dev.forest.quiz.model.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.quiz.model.dto.QuizDTO;

@Mapper
public interface QuizMapper {

	List<QuizDTO> findAll();
	
	QuizDTO findById(Long quizNo);

	List<QuizDTO> findByCategory(String category);

	QuizDTO findByRandomNo(Object next);

	int selectTotalCount();

}
