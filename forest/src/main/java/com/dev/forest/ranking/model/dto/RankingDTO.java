package com.dev.forest.ranking.model.dto;

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
public class RankingDTO {
	private Long divisionNo;
	private Long userNo;
	private String nickName;
	private Long correct;
	private Long wrong;
	private Date testDate;
	
}
