package com.dev.forest.quiz.model.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.quiz.model.dto.QuizDTO;
import com.dev.forest.quiz.model.dto.QuizOptionDTO;

@Mapper
public interface QuizMapper {

	List<QuizDTO> findByCategory(String category);

	QuizDTO findByRandomNo(Object next);

	int selectTotalCount();

	List<QuizOptionDTO> quizOption(Object quizNo);

}
