package com.dev.forest.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dev.forest.auth.model.service.AuthenticationService;
import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.board.model.dto.BoardDTO;
import com.dev.forest.board.model.mapper.BoardMapper;
import com.dev.forest.common.model.dto.PageInfo;
import com.dev.forest.common.template.Pagination;
import com.dev.forest.exception.AccessDeniedException;
import com.dev.forest.exception.BoardNotFoundException;

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
	@Transactional
	public void save(BoardDTO board, int boardType, MultipartFile file) {

//		log.info("게시글정보 : {} \n 파일정보 : {} ",board, file, boardType);

		CustomUserDetails user = authService.getAuthenticatedUser();
		log.info("게시판 작성자 : {}", board.getBoardWriter());
		log.info("로그인 우저 : {}", user.getUsername());

		// 파일확인
		if (file != null && !file.isEmpty()) {
			String filePath = fileService.store(file, "BoardImg");
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
		} else if(boardType == 3) {
			boardMapper.saveInfo(board);
		}

	}

	private int getTotalCount(int boardType) {
		return boardMapper.selectTotalCount(boardType);
	}

	private void incrementViewCount(Long boardNo) {
		int result = boardMapper.increaseCount(boardNo);
		if(result < 1) {
			throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String,Object> findAll(int boardType, int page) {
		int totalCount = getTotalCount(boardType);
		PageInfo pi = Pagination.getPageInfo(totalCount, page, 5);

		List<BoardDTO> boardList = boardMapper.findAll(pi.offset(), pi.getBoardLimit(), boardType);
		HashMap<String, Object> map = new HashMap<>();
		map.put("boardList", boardList);
		map.put("pi", pi);
		
		return map;
	}

	private BoardDTO getBoardOrThrow(Long boardNo) {
		BoardDTO board = boardMapper.findById(boardNo); // 게시판 상세보기

		if (board == null) {
			throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
		}

		return board; // 게시판 반환
	}

	@Override
	@Transactional
	public BoardDTO findById(Long boardNo) {
		incrementViewCount(boardNo);
		return getBoardOrThrow(boardNo);
	}

	@Override
	@Transactional
	public BoardDTO update(BoardDTO board, MultipartFile file) {
		BoardDTO exsitingBoard = getBoardOrThrow(board.getBoardNo()); // 특정 게시판 출력

		// 작성자 본인인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		if (!exsitingBoard.getWriterNo().equals(user.getUserNo())) {
			throw new AccessDeniedException("요청한 사용자와 게시글 작성자가 일치하지 않습니다.");
		}

		// 바뀐 제목, 내용 입력
		exsitingBoard.setBoardTitle(board.getBoardTitle());
		exsitingBoard.setBoardContent(board.getBoardContent());

		if (file != null && !file.isEmpty()) {
			String filePath = fileService.store(file, "BoardImg");
			exsitingBoard.setBoardFileUrl(filePath);
		}

		boardMapper.update(exsitingBoard);
		return exsitingBoard;
	}

	@Override
	@Transactional
	public void delete(Long boardNo) {
		BoardDTO exsitingBoard = getBoardOrThrow(boardNo); // 특정 게시판 출력

		// 작성자 본인인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		if (!exsitingBoard.getWriterNo().equals(user.getUserNo())) {
			throw new AccessDeniedException("요청한 사용자와 게시글 작성자가 일치하지 않습니다.");
		}

		boardMapper.delete(exsitingBoard); // 게시판 삭제(상태 N으로 변환)
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map<String,Object> search(int boardType, String condition, String keyword, int page) {
		Pagination.validateKeyword(keyword);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", keyword);
		params.put("condition", condition);
		params.put("boardType", boardType);

		int totalCount = boardMapper.searchCount(params);
		PageInfo pageInfo = Pagination.getPageInfo(totalCount, page, 5);
		params.put("offset", pageInfo.offset());
		params.put("limit", pageInfo.getBoardLimit());

		List<BoardDTO> boardList = boardMapper.search(params);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pi", pageInfo);
		map.put("boardList", boardList);
		
		return map;
	}

}
