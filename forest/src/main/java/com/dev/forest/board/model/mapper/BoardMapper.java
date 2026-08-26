package com.dev.forest.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dev.forest.board.model.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	void saveBasic(BoardDTO board);

	void saveNotice(BoardDTO board);

	void saveInfo(BoardDTO board);

	@Select("SELECT COUNT(*) FROM TB_BOARD WHERE STATUS = 'Y' AND BOARD_TYPE = #{boardType}")
	int selectTotalCount(int boardType);

	List<BoardDTO> findAll(@Param("offset") int offset, @Param("limit") int limit, @Param("boardType") int boardType);

	BoardDTO findById(Long boardNo);

	@Select("SELECT COUNT(*) FROM TB_BOARD WHERE STATUS = 'Y' AND BOARD_NO = #{boardNo}")
	int existsById(Long boardNo);

	int increaseCount(Long boardNo);

	void update(BoardDTO existingBoard);

	void delete(BoardDTO existingBoard);

	int searchCount(Map<String, Object> params);

	List<BoardDTO> search(Map<String, Object> params);

}
