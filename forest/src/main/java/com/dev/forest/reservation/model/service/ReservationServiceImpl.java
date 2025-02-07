package com.dev.forest.reservation.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.forest.auth.model.service.AuthenticationService;
import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.board.model.service.FileService;
import com.dev.forest.common.model.dto.PageInfo;
import com.dev.forest.common.template.Pagination;
import com.dev.forest.exception.BoardNotFoundException;
import com.dev.forest.exception.InvalidParameterException;
import com.dev.forest.reservation.model.dto.ReservationDTO;
import com.dev.forest.reservation.model.mapper.ReservationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	
	private final ReservationMapper reservationMapper;
	private final FileService fileService;
	private final AuthenticationService authService;
	
	@Override
	public void reservate(ReservationDTO reservation, MultipartFile file) {
		// 검증된 인원인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		authService.validWriter(reservation.getReservationUser(), user.getUsername());
		
		// 파일 확인
		if (file != null && !file.isEmpty()) {
			String filePath = fileService.store(file);
			reservation.setFileUrl(filePath);
		}
		
		// 모임 등록
		reservationMapper.reservate(reservation);
	}
	
	private int getTotalCount() {
		int totalCount = reservationMapper.selectTotalCount();
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
	public List<ReservationDTO> findAll(int page) {
		int totalCount = getTotalCount();
		PageInfo pi = getPageInfo(totalCount, page);
		RowBounds rowBounds = paging(pi);
		return reservationMapper.findAll(rowBounds);
	}
	
	private ReservationDTO getBoardOrThrow(Long reservationNo) {
		ReservationDTO reservation = reservationMapper.findById(reservationNo); // 게시판 상세보기
		
		if (reservation == null) {
			throw new InvalidParameterException("올바른 게시판 번호가 아닙니다."); // 오류처리
		}
		
		return reservation; // 이미지있는 게시판 반환
	}
	
	@Override
	public ReservationDTO findById(Long reservationNo) {
		return getBoardOrThrow(reservationNo);
	}

	@Override
	public void delete(Long reservationNo) {
		ReservationDTO exsitingReservation = getBoardOrThrow(reservationNo);
		
		// 검증된 인원인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		authService.validWriter(exsitingReservation.getReservationUser(), user.getUsername());
		
		reservationMapper.delete(reservationNo);
	}
	
	private void validateKeyword(String keyword) {
		if(keyword == null || keyword.trim().isEmpty()) {
			throw new InvalidParameterException("검색어를 입력해주세요.");
		}
	}

	@Override
	public List<ReservationDTO> search(String keyword, String condition, int page) {
		validateKeyword(keyword);
		
		int totalCount = reservationMapper.searchCount(keyword, condition);
		
		PageInfo pageInfo = getPageInfo(totalCount, page);
		
		List<ReservationDTO> list = reservationMapper.search(keyword, condition, paging(pageInfo));
		
		return list;
	}

}
