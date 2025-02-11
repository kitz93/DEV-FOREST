package com.dev.forest.board.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.forest.auth.model.service.AuthenticationService;
import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.board.model.dto.BoardDTO;
import com.dev.forest.board.model.mapper.BoardMapper;
import com.dev.forest.common.model.dto.PageInfo;
import com.dev.forest.common.template.Pagination;
import com.dev.forest.exception.BoardNotFoundException;
import com.dev.forest.exception.InvalidParameterException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	private final FileService fileService;
	private final AuthenticationService authService;
	
	@Override
	public void save(BoardDTO board, int boardType, MultipartFile file) {
		
		log.info("게시글정보 : {} \n 파일정보 : {} ",board, file, boardType);

		// 검증된 인원인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		authService.validWriter(board.getBoardWriter(), user.getUsername());
		
		// 파일확인
		if (file != null && !file.isEmpty()) {
			String filePath = fileService.store(file);
			board.setBoardFileUrl(filePath);
		} else {
			board.setBoardFileUrl(null);
		}
		
		board.setBoardWriter(String.valueOf(user.getUserNo()));

		// 게시판 저장(type에 따라 다른 게시판 저장방식 적용)
		if (boardType == 1) {
			boardMapper.saveBasic(board);
		} else if (boardType == 2) {
			boardMapper.saveNotice(board);
		} else {
			boardMapper.saveInfo(board);
		}

	}

	private int getTotalCount(int boardType) {
		int totalCount = boardMapper.selectTotalCount(boardType);
		if (totalCount == 0) {
			throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
		}
		return totalCount;
	}

	private PageInfo getPageInfo(int totalCount, int page) {
		return Pagination.getPageInfo(totalCount, page, 10);
	}
	
	private RowBounds paging(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return rowBounds;
	}

	@Override
	public List<BoardDTO> findAll(int boardType, int page) {
		int totalCount = getTotalCount(boardType);
		PageInfo pi = getPageInfo(totalCount, page);
		RowBounds rowBounds = paging(pi);
		return boardMapper.findAll(rowBounds, boardType);
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
		CustomUserDetails user = authService.getAuthenticatedUser();
		authService.validWriter(board.getBoardWriter(), user.getUsername());

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
		CustomUserDetails user = authService.getAuthenticatedUser();
		authService.validWriter(exsitingBoard.getBoardWriter(), user.getUsername());

		boardMapper.delete(exsitingBoard); // 게시판 삭제(상태 N으로 변환)
	}
	
	private void validateKeyword(String keyword) {
		if(keyword == null || keyword.trim().isEmpty()) {
			throw new InvalidParameterException("검색어를 입력해주세요.");
		}
	}

	@Override
	public List<BoardDTO> search(int boardType, String condition, String keyword, int page) {
		validateKeyword(keyword);
		
		int totalCount = boardMapper.searchCount(keyword, condition, boardType);
		
		PageInfo pageInfo = getPageInfo(totalCount, page);
		
		List<BoardDTO> list = boardMapper.search(keyword, condition, boardType, paging(pageInfo));
		
		return list;
	}

}
