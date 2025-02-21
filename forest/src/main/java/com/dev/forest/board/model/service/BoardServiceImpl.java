package com.dev.forest.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	private final FileService fileService;
	private final AuthenticationService authService;
	private final MemberMapper memberMapper;
	
	@Override
	public void save(BoardDTO board, int boardType, MultipartFile file) {
		
//		log.info("게시글정보 : {} \n 파일정보 : {} ",board, file, boardType);

		// 검증된 인원인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		log.info("게시판 작성자 : {}", board.getBoardWriter());
		log.info("로그인 우저 : {}", user.getUsername());
		authService.validWriter(board.getBoardWriter(), user.getNickname());
		
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
		int totalCount = boardMapper.selectTotalCount(boardType);
		if (totalCount == 0) {
			throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
		}
		return totalCount;
	}

	private PageInfo getPageInfo(int totalCount, int page) {
		return Pagination.getPageInfo(totalCount, page, 5);
	}
	
	private RowBounds paging(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return rowBounds;
	}
	
	private void incrementViewCount(Long boardNo) {
		int result = boardMapper.increaseCount(boardNo);
		if(result < 1) {
			throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
		}
	}

	@Override
	public Map<String,Object> findAll(int boardType, int page) {
		int totalCount = getTotalCount(boardType);
		PageInfo pi = getPageInfo(totalCount, page);
		RowBounds rowBounds = paging(pi);
		
		List<BoardDTO> boardList = boardMapper.findAll(rowBounds, boardType);
		HashMap<String, Object> map = new HashMap<>();
		map.put("boardList", boardList);
		map.put("pi", pi);
		
		return map;
	}

	private BoardDTO getBoardOrThrow(Long boardNo) {
		BoardDTO board = boardMapper.findById(boardNo); // 게시판 상세보기
		
		if (board == null) {
			throw new InvalidParameterException("올바른 게시판 번호가 아닙니다."); // 오류처리
		}
		
		return board; // 게시판 반환
	}

	@Override
	public BoardDTO findById(Long boardNo) {
		incrementViewCount(boardNo);
		return getBoardOrThrow(boardNo);
	}

	@Override
	public BoardDTO update(BoardDTO board, MultipartFile file) {
		BoardDTO exsitingBoard = getBoardOrThrow(board.getBoardNo()); // 특정 게시판 출력

		// 검증된 인원인지 파악
		CustomUserDetails user = authService.getAuthenticatedUser();
		authService.validWriter(board.getBoardWriter(), user.getNickname());

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
	public void delete(Long boardNo) {
		BoardDTO exsitingBoard = getBoardOrThrow(boardNo); // 특정 게시판 출력
		
		// 검증된 인원인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		
		MemberDTO userNickname = memberMapper.findByUserId(user.getUsername());
		authService.validWriter(exsitingBoard.getBoardWriter(), userNickname.getNickname());

		boardMapper.delete(exsitingBoard); // 게시판 삭제(상태 N으로 변환)
	}
	
	private void validateKeyword(String keyword) {
		if(keyword == null || keyword.trim().isEmpty()) {
			throw new InvalidParameterException("검색어를 입력해주세요.");
		}
	}

	@Override
	public Map<String,Object> search(int boardType, String condition, String keyword, int page) {
		validateKeyword(keyword);
		
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("keyword", keyword);
		 params.put("condition", condition);
		 params.put("boardType", boardType);
		
		int totalCount = boardMapper.searchCount(params);
		PageInfo pageInfo = getPageInfo(totalCount, page);
		RowBounds rowBounds = paging(pageInfo);
		
		List<BoardDTO> boardList = boardMapper.search(rowBounds, params);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pi", pageInfo);
		map.put("boardList", boardList);
		
		return map;
	}

}
