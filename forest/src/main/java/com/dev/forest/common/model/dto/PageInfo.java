package com.dev.forest.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@Builder
public class PageInfo {
	private int listCount;
	private int currentPage;
	private int boardLimit;
	private int maxPage;
	
}

