package com.dev.forest.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.dev.forest.board.model.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	void saveBasic(BoardDTO board);

	void saveNotice(BoardDTO board);

	void saveInfo(BoardDTO board);

	@Select("SELECT COUNT(*) FROM TB_BOARD WHERE STATUS = 'Y' AND BOARD_TYPE = #{boardType}")
	int selectTotalCount(int boardType);

	List<BoardDTO> findAll(RowBounds rowBounds, int boardType);

	BoardDTO findById(Long boardNo);

	void update(BoardDTO exsitingBoard);

	void delete(BoardDTO exsitingBoard);

	int searchCount(String keyword, String condition, int boardType);

	List<BoardDTO> search(String keyword, String condition, int boardType, RowBounds paging);
	
	

}
