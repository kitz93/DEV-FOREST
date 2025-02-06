package com.dev.forest.board.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dev.forest.board.model.dto.BoardDTO;

public interface BoardService {

	void save(BoardDTO board, int boardType, MultipartFile file);

	List<BoardDTO> findAll(int boardType, int page);

	BoardDTO findById(Long boardNo);

	BoardDTO update(BoardDTO board, MultipartFile file);

	void delete(Long boardNo);

}
