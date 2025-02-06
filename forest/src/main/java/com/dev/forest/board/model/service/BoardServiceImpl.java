package com.dev.forest.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.forest.board.model.dto.BoardDTO;
import com.dev.forest.board.model.mapper.BoardMapper;
import com.dev.forest.exception.BoardNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper boardMapper;
	private final FileService fileService;
	
	@Override
	public void save(BoardDTO board, int boardType, MultipartFile file) {
		
		// 검증된 인원인지 확인
		
		// 파일확인
		if(boardType == 1) {
			boardMapper.saveBasic(board);
		} else if(boardType == 2) {
			boardMapper.saveNotice(board);
		} else {
			boardMapper.saveInfo(board);
		}
		
	}
	
	private int getTotalCount() {
		int totalCount = boardMapper.selectTotalCount();
		if(totalCount == 0) {
			throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
		}
		return totalCount;
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
