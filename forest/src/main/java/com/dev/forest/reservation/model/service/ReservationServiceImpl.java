package com.dev.forest.reservation.model.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.mapper.MemberMapper;
import com.dev.forest.reservation.model.dto.ReservationDTO;
import com.dev.forest.reservation.model.mapper.ReservationMapper;
import com.dev.forest.studying.model.dto.StudyingDTO;
import com.dev.forest.studying.model.mapper.StudyingMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {
	
	private final ReservationMapper reservationMapper;
	private final FileService fileService;
	private final AuthenticationService authService;
	private final StudyingMapper studyingMapper;
	private final MemberMapper memberMapper;
	
	@Override
	public void reservate(ReservationDTO reservation, MultipartFile file) {
		
//		log.info("게시글정보 : {} \n 파일정보 : {} ",reservation, file);
		
		// 검증된 인원인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		authService.validWriter(reservation.getReservationUser(), user.getNickname());
		
		// 파일 확인
		if (file != null && !file.isEmpty()) {
			String filePath = fileService.store(file, "RservationImg");
			reservation.setFileUrl(filePath);
		} else {
			throw new InvalidParameterException("모임 대표사진이 누락되었습니다.");
		}
		
		reservation.setReservationUser(String.valueOf(user.getUserNo()));
		
		LocalDateTime now = LocalDateTime.now();
		
		if(reservation.getStartTime().isBefore(now) || reservation.getEndTime().isBefore(now)) {
			throw new InvalidParameterException("시작시간 또는 종료시간이 현재시간 전입니다.");
		} else if (reservation.getStartTime().isAfter(reservation.getEndTime())) {
			throw new InvalidParameterException("시작시간이 종료시간보다 이후의 시간입니다.");
		}
		
		// 모임 등록
		reservationMapper.reservate(reservation);
		
		Long reservationNo = reservation.getReservationNo();  // 등록 후 생성된 ID (자동 증가된 키)
		
//		log.info("번호번호 : {}", reservationNo);

		if (reservationNo == null) {
			throw new BoardNotFoundException("모임 등록 후 ID를 가져올 수 없습니다.");
		}

		StudyingDTO studying = StudyingDTO.builder()
		        						  .refRno(reservationNo) // 모임 번호
		        						  .studyingUser(reservation.getReservationUser()) // 등록자 (참석자)
		        						  .build();

		studyingMapper.attend(studying);
	}
	
	private int getTotalCount() {
		int totalCount = reservationMapper.selectTotalCount();
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

	@Override
	public Map<String, Object> findAll(int page) {
		int totalCount = getTotalCount();
		PageInfo pi = getPageInfo(totalCount, page);
		RowBounds rowBounds = paging(pi);
		
		List<ReservationDTO> reservationList = reservationMapper.findAll(rowBounds);
		
		LocalDateTime now = LocalDateTime.now();
		
		for(ReservationDTO reservation : reservationList) {
			if(reservation.getEndTime() != null && reservation.getEndTime().isBefore(now)) {				
				reservationMapper.updateToExpired(reservation.getReservationNo());
			}
		}
		
		List<ReservationDTO> list = reservationMapper.findAll(rowBounds);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("reservationList", list);
		map.put("pi", pi);
		
		return map;
	}
	
	private ReservationDTO getBoardOrThrow(Long reservationNo) {
		ReservationDTO reservation = reservationMapper.findById(reservationNo); // 게시판 상세보기
		
		if (reservation == null) {
			throw new InvalidParameterException("올바른 게시판 번호가 아닙니다."); // 오류처리
		}
		
		return reservation;
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
		
		MemberDTO userNickname = memberMapper.findByUserId(user.getUsername());
		
		authService.validWriter(exsitingReservation.getReservationUser(),userNickname.getNickname());
		
		reservationMapper.delete(exsitingReservation);
	}
	
	private void validateKeyword(String keyword) {
		if(keyword == null || keyword.trim().isEmpty()) {
			throw new InvalidParameterException("검색어를 입력해주세요.");
		}
	}

	@Override
	public Map<String, Object> search(String keyword, String condition, int page) {
		validateKeyword(keyword);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", keyword);
		params.put("condition", condition);
		
		int totalCount = reservationMapper.searchCount(params);
		PageInfo pageInfo = getPageInfo(totalCount, page);
		RowBounds rowBounds = paging(pageInfo);
		
		List<ReservationDTO> list = reservationMapper.search(rowBounds, params);
		Map<String, Object> map = new HashMap<>();
		map.put("reservationList",list);
		map.put("pi", pageInfo);
		
		return map;
	}

}
