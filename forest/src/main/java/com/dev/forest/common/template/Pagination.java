package com.dev.forest.common.template;

import com.dev.forest.common.model.dto.PageInfo;

public class Pagination {
	
	public static PageInfo getPageInfo(int listCount, int currentPage, int boardLimit) {
		
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
	
		return PageInfo.builder()
					   .listCount(listCount)
					   .currentPage(currentPage)
					   .boardLimit(boardLimit)
					   .maxPage(maxPage)
					   .build();
	}

}
