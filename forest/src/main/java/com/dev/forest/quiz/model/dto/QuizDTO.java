package com.dev.forest.quiz.model.dto;

import jakarta.validation.constraints.NotBlank;
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
public class QuizDTO {
	private Long quizNo;
	@NotBlank
	private String question;
	@NotBlank
	private String category;
	@NotBlank
	private String questionType;
	@NotBlank
	private String answer;
	@NotBlank
	private String explanation;

}
