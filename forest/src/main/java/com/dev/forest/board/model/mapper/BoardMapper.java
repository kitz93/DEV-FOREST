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

	@Select("SELECT COUNT(*) FROM TB_BOARD WHERE STATUS = 'Y'")
	int selectTotalCount();

	List<BoardDTO> findAll(RowBounds rowBounds);

	BoardDTO findById(Long boardNo);

	BoardDTO findByIdWithoutImg(Long boardNo);

	void update(BoardDTO exsitingBoard);

	void delete(BoardDTO exsitingBoard);
	
	

}
