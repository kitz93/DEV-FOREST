package com.dev.forest.studying.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.forest.auth.model.service.AuthenticationService;
import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.exception.DuplicateAttendException;
import com.dev.forest.exception.PullCountStudyingException;
import com.dev.forest.exception.ReservationNotFoundException;
import com.dev.forest.exception.UserNotFoundException;
import com.dev.forest.reservation.model.mapper.ReservationMapper;
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

	@Override
	@Transactional
	public void attend(StudyingDTO studying) {

		// 모임 존재 확인 + 정원 체크용 락(같은 모임에 대한 attend/cancel을 직렬화)
		Integer maxCapacity = reservationMapper.getMaxCountForUpdate(studying.getRefRno());
		if (maxCapacity == null) {
			throw new ReservationNotFoundException("존재하지 않는 모임입니다.");
		}

		CustomUserDetails user = authService.getAuthenticatedUser();

		if (studyingMapper.existsAttendee(studying.getRefRno(), user.getUserNo()) > 0) {
			throw new DuplicateAttendException("이미 참석 중인 모임입니다.");
		}

		int currentCount = studyingMapper.countByReservationNo(studying.getRefRno());

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
	@Transactional(readOnly = true)
	public List<StudyingDTO> findByReservationNo(Long refBno) {
		return studyingMapper.findByReservationNo(refBno);
	}

	@Override
	@Transactional
	public void cancel(Long refBno) {

		// 모임 존재 확인 + 정원 체크용 락(attend와 동일한 락으로 직렬화)
		Integer maxCapacity = reservationMapper.getMaxCountForUpdate(refBno);
		if (maxCapacity == null) {
			throw new ReservationNotFoundException("존재하지 않는 모임입니다.");
		}

		// 참석자 본인인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		if (studyingMapper.existsAttendee(refBno, user.getUserNo()) == 0) {
	    	throw new UserNotFoundException("해당 모임에 참석자가 아닙니다.");
	    }

		Map<String, Object> params = new HashMap<>();
		params.put("refBno", refBno);
		params.put("studyingUser", user.getUserNo());

		studyingMapper.cancel(params);

		int currentCount = studyingMapper.countByReservationNo(refBno);

		if (currentCount < maxCapacity) {
			reservationMapper.notPullReservationStatus(refBno);
		}
	}

}
