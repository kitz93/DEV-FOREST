package com.dev.forest.reservation.model.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.dev.forest.reservation.model.dto.ReservationDTO;

public interface ReservationService {

	void reservate(ReservationDTO reservation, MultipartFile file);

	Map<String, Object> findAll(int page);

	ReservationDTO findById(Long reservationNo);

	void delete(Long reservationNo);
	
	Map<String, Object> search(String keyword, String condition, int page);

}
