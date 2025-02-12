package com.dev.forest.studying.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.forest.auth.model.service.AuthenticationService;
import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.exception.PullCountStudyingException;
import com.dev.forest.exception.UserNotFoundException;
import com.dev.forest.reservation.model.mapper.ReservationMapper;
import com.dev.forest.reservation.model.service.ReservationService;
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
		
		int currentCount = studyingMapper.countByReservationNo(studying.getRefRno());
		
		int maxCapacity = reservationMapper.getMaxCount(studying.getRefRno());
		
		if (currentCount >= maxCapacity) {
	        throw new PullCountStudyingException("모임 정원이 초과되었습니다.");
	    }
		
		studying.setStudyingUser(String.valueOf(user.getUserNo()));
		
		// 모임 참석
		studyingMapper.attend(studying);
		
		if (currentCount + 1 == maxCapacity) {
	        reservationMapper.pullReservationStatus(studying.getRefRno());
	    }
		
	}

	@Override
	public List<StudyingDTO> findByRervationNo(Long refBno) {
		return studyingMapper.findByRervationNo(refBno);
	}

	@Override
	public void cancle(Long refBno) {
		
		// 모임이 존재하는지 확인
		reservationService.findById(refBno);
		
		// 로그인 인원이 리스트안에 있는지 확인
		List<StudyingDTO> list = findByRervationNo(refBno);
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
	    	studyingMapper.cancle(refBno);
	    }
	    
	    int currentCount = studyingMapper.countByReservationNo(refBno);
		int maxCapacity = reservationMapper.getMaxCount(refBno);
		
		if (currentCount < maxCapacity) {
	        reservationMapper.notPullReservationStatus(refBno);
	    }
		
	}

}
