package com.dev.forest.studying.model.service;

import java.util.List;

import com.dev.forest.studying.model.dto.StudyingDTO;

public interface StudyingService {

	void attend(StudyingDTO studying);

	List<StudyingDTO> findByReservationNo(Long reservationNo);

	void cancel(Long reservationNo);

}
