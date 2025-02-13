package com.dev.forest.board.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.dev.forest.board.model.dto.BoardDTO;

public interface BoardService {

	void save(BoardDTO board, int boardType, MultipartFile file);

	Map<String, Object> findAll(int boardType, int page);

	BoardDTO findById(Long boardNo);

	BoardDTO update(BoardDTO board, MultipartFile file);

	void delete(Long boardNo);

	Map<String, Object> search(int boardType, String condition, String keyword, int page);

}
