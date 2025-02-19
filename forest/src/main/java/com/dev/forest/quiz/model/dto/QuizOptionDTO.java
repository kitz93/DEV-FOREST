package com.dev.forest.quiz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@ToString
public class QuizOptionDTO {
	
	private Long quizNo;
	private String quizOption;

}
