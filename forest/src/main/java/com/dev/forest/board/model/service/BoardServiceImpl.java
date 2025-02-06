package com.dev.forest.board.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.forest.board.model.dto.BoardDTO;
import com.dev.forest.board.model.mapper.BoardMapper;
import com.dev.forest.common.model.dto.PageInfo;
import com.dev.forest.common.template.Pagination;
import com.dev.forest.exception.BoardNotFoundException;
import com.dev.forest.exception.InvalidParameterException;

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
		if (file != null && !file.isEmpty()) {
			String filePath = fileService.store(file);
			board.setBoardFileUrl(filePath);
		} else {
			board.setBoardFileUrl(null);
		}

		// 게시판 저장(type에 따라 다른 게시판 저장방식 적용)
		if (boardType == 1) {
			boardMapper.saveBasic(board);
		} else if (boardType == 2) {
			boardMapper.saveNotice(board);
		} else {
			boardMapper.saveInfo(board);
		}

	}

	private int getTotalCount() {
		int totalCount = boardMapper.selectTotalCount();
		if (totalCount == 0) {
			throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
		}
		return totalCount;
	}

	private PageInfo getPageInfo(int totalCount, int page) {
		return Pagination.getPageInfo(totalCount, page, 10);
	}

	@Override
	public List<BoardDTO> findAll(int boardType, int page) {
		int totalCount = getTotalCount();
		PageInfo pi = getPageInfo(totalCount, page);

		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return boardMapper.findAll(rowBounds);
	}

	private BoardDTO getBoardOrThrow(Long boardNo) {
		BoardDTO board = boardMapper.findById(boardNo); // 이미지가 있는 게시판 상세보기
		
		if (board == null) {
			throw new InvalidParameterException("올바른 게시판 번호가 아닙니다."); // 오류처리
		}
		
		return board; // 이미지있는 게시판 반환
	}

	@Override
	public BoardDTO findById(Long boardNo) {
		return getBoardOrThrow(boardNo);
	}

	@Override
	public BoardDTO update(BoardDTO board, MultipartFile file) {
		BoardDTO exsitingBoard = getBoardOrThrow(board.getBoardNo()); // 특정 게시판 출력

		// 검증된 인원인지 파악

		// 바뀐 제목, 내용 입력
		exsitingBoard.setBoardTitle(board.getBoardTitle());
		exsitingBoard.setBoardContent(board.getBoardContent());

		if (file != null && !file.isEmpty()) {
			String filePath = fileService.store(file);
			exsitingBoard.setBoardFileUrl(filePath);
		}

		boardMapper.update(exsitingBoard);
		return exsitingBoard;
	}

	@Override
	public void delete(Long boardNo) {
		BoardDTO exsitingBoard = getBoardOrThrow(boardNo); // 특정 게시판 출력

		// 검증된 인원인지 확인

		boardMapper.delete(exsitingBoard); // 게시판 삭제(상태 N으로 변환)
	}

}
