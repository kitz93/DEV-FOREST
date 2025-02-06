package com.dev.forest.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.forest.board.model.dto.BoardDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {@Override
	public void save(BoardDTO board, MultipartFile file) {
		
	}

	@Override
	public List<BoardDTO> findAll(int page) {
		return null;
	}

	@Override
	public BoardDTO findById(Long boardNo) {
		return null;
	}

	@Override
	public BoardDTO update(BoardDTO board, MultipartFile file) {
		return null;
	}

	@Override
	public void delete(Long boardNo) {
		
	}

}
