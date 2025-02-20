package com.dev.forest.wrong.model.dto;

import java.sql.Date;

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
public class WrongDTO {
	
	private Long answerNo;
	private Long quizNo;
	private Long userNo;
	private Date testDate;
	private String entAnswer;

}
