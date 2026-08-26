package com.dev.forest.common.template;

import com.dev.forest.common.model.dto.PageInfo;
import com.dev.forest.exception.InvalidParameterException;

public class Pagination {

	public static PageInfo getPageInfo(int listCount, int currentPage, int boardLimit) {

		int maxPage = (int)Math.ceil((double)listCount / boardLimit);

		if (currentPage < 1) {
			currentPage = 1;
		} else if (maxPage >= 1 && currentPage > maxPage) {
			currentPage = maxPage;
		}

		return PageInfo.builder()
					   .listCount(listCount)
					   .currentPage(currentPage)
					   .boardLimit(boardLimit)
					   .maxPage(maxPage)
					   .build();
	}

	public static void validateKeyword(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			throw new InvalidParameterException("검색어를 입력해주세요.");
		}
	}

}
