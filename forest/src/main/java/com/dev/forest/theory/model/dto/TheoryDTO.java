package com.dev.forest.theory.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@ToString
@Getter
@Setter
public class TheoryDTO {
	
	private Long subjectNo;
	private String subject;
	private String content;

}
