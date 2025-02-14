package com.dev.forest.progress.model.dto;

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
public class ProgressDTO {
	
	private Long userNo;
	private Long subjectNo;
	private String status;

}
