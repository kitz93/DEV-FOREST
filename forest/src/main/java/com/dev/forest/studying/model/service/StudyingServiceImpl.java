package com.dev.forest.studying.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.forest.auth.model.service.AuthenticationService;
import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.exception.PullCountStudyingException;
import com.dev.forest.exception.UserNotFoundException;
import com.dev.forest.member.model.dto.MemberDTO;
import com.dev.forest.member.model.mapper.MemberMapper;
import com.dev.forest.reservation.model.mapper.ReservationMapper;
import com.dev.forest.reservation.model.service.ReservationService;
import com.dev.forest.studying.model.dto.StudyingDTO;
import com.dev.forest.studying.model.mapper.StudyingMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class StudyingServiceImpl implements StudyingService {
	
	private final StudyingMapper studyingMapper;
	private final ReservationMapper reservationMapper;
	private final AuthenticationService authService;
	private final ReservationService reservationService;
	private final MemberMapper memberMapper;
	
	@Override
	public void attend(StudyingDTO studying) {
		
		// 모임있는지 여부 확인
		reservationMapper.findById(studying.getRefRno());
		
		// 검증된 인원인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		authService.validWriter(studying.getStudyingUser(), user.getNickname());
		
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
		MemberDTO userNickname = memberMapper.findByUserId(user.getUsername());
		
		boolean isAttendee = false;
	    for (StudyingDTO studying : list) {
	        if (studying.getStudyingUser().equals(userNickname.getNickname())) {
	            isAttendee = true;
	            break;
	        }
	    }
	    
	    if(!isAttendee) {
	    	throw new UserNotFoundException("해당 모임에 참석자가 아닙니다.");
	    } else {
	    	Map<String, Object> params = new HashMap<>();
	    	params.put("refBno", refBno);
	    	params.put("studyingUser", user.getUserNo());
	    	
	    	studyingMapper.cancle(params);
	    	
	    	int currentCount = studyingMapper.countByReservationNo(refBno);
			int maxCapacity = reservationMapper.getMaxCount(refBno);
			
			if (currentCount < maxCapacity) {
		        reservationMapper.notPullReservationStatus(refBno);
		    }
	    }
		
	}

}
