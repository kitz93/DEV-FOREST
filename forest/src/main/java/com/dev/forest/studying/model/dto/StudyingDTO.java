package com.dev.forest.studying.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudyingDTO {

	private Long studyingNo;
	private Long refRno;
	private String studyingUser;
	private String createDate;
	private String status;
}
