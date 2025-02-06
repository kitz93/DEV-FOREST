package com.dev.forest.board.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.dev.forest.board.model.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	void saveBasic(BoardDTO board);

	void saveNotice(BoardDTO board);

	void saveInfo(BoardDTO board);

	@Select("SELECT COUNT(*) FROM TB_BOARD WHERE STATUS = 'Y'")
	int selectTotalCount();
	
	

}
