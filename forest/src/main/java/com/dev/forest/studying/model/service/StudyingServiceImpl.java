package com.dev.forest.studying.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.forest.auth.model.service.AuthenticationService;
import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.board.model.dto.BoardDTO;
import com.dev.forest.exception.UserNotFoundException;
import com.dev.forest.reservation.model.dto.ReservationDTO;
import com.dev.forest.reservation.model.mapper.ReservationMapper;
import com.dev.forest.reservation.model.service.ReservationService;
import com.dev.forest.reservation.model.service.ReservationServiceImpl;
import com.dev.forest.studying.model.dto.StudyingDTO;
import com.dev.forest.studying.model.mapper.StudyingMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudyingServiceImpl implements StudyingService {
	
	private final StudyingMapper studyingMapper;
	private final ReservationMapper reservationMapper;
	private final AuthenticationService authService;
	private final ReservationService reservationService;
	
	@Override
	public void attend(StudyingDTO studying) {
		
		// 모임있는지 여부 확인
		reservationMapper.findById(studying.getRefRno());
		
		// 검증된 인원인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		authService.validWriter(studying.getStudyingUser(), user.getUsername());
		
		// 모임 참석
		studyingMapper.attend(studying);
		
	}

	@Override
	public List<StudyingDTO> findByRervationNo(Long reservationNo) {
		return studyingMapper.findByRervationNo(reservationNo);
	}
	
	public int countReservationByNo(Long reservationNo) {
		
		int totalCount = studyingMapper.countReservationByNo(reservationNo);
		return totalCount;
	}
	
	
	
	

	@Override
	public void cancle(Long reservationNo) {
		
		// 모임이 존재하는지 확인
		reservationService.findById(reservationNo);
		
		// 로그인 인원이 리스트안에 있는지 확인
		List<StudyingDTO> list = findByRervationNo(reservationNo);
		CustomUserDetails user = authService.getAuthenticatedUser();
		
		StudyingDTO studyingUser = null;
	    for (StudyingDTO studying : list) {
	        if (studying.getStudyingUser().equals(user.getUsername())) {
	            studyingUser = studying;
	            break;
	        }
	    }
	    
	    // 리스트에 존재하지 않으면 예외 발생
	    if (studyingUser == null) {
	        throw new UserNotFoundException("모임에 참석하지 않은 사용자입니다.");
	    } else {
	    	studyingMapper.cancle(reservationNo);
	    }
		
	}

}
