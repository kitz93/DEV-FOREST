package com.dev.forest.quiz.model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.dev.forest.exception.InvalidParameterException;
import com.dev.forest.quiz.model.dto.QuizDTO;
import com.dev.forest.quiz.model.mapper.QuizMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {
	
	private final QuizMapper quizMapper;
	
	@Override
	public void insertQuiz(QuizDTO quiz) {
		log.info("퀴즈 정보 : {}", quiz);
	}

	@Override
	public List<QuizDTO> findAll() {
		return quizMapper.findAll();
	}
	
	private QuizDTO getQuizOrThrow(Long quizNo) {
		QuizDTO quiz = quizMapper.findById(quizNo);
		if(quiz == null) {
			throw new InvalidParameterException("올바르지 않은 퀴즈 번호입니다.");
		}
		return quiz;
	}

	@Override
	public QuizDTO findById(Long quizNo) {
		return getQuizOrThrow(quizNo);
	}

	private List<QuizDTO> getQuizsOrThrow(String category) {
		List<QuizDTO> quizList = quizMapper.findByCategory(category);
		if(quizList.isEmpty()) {
			throw new InvalidParameterException("올바르지 않은 카테고리입니다.");
		}
		return quizList;
	}
	
	@Override
	public List<QuizDTO> findByCategory(String category) {
		return getQuizsOrThrow(category);
	}
	
	private int getTotalCount() {
		int totalCount = quizMapper.selectTotalCount();
		return totalCount;
	}

	@Override
	public List<QuizDTO> findByRandomNo() {
		int totalCount = getTotalCount();
		List<QuizDTO> quizList = new ArrayList();
		Set<Integer> randomNo = new HashSet();
		while(randomNo.size() < 5) {
			int num = (int)(Math.random() * totalCount) + 1;
			randomNo.add(num);
		}
		
		Iterator iter = randomNo.iterator();
		while(iter.hasNext()) {
			QuizDTO quiz = quizMapper.findByRandomNo(iter.next());
			quizList.add(quiz);
		}
		return quizList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
