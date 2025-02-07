package com.dev.forest.reservation.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dev.forest.reservation.model.dto.ReservationDTO;

public interface ReservationService {

	void reservate(ReservationDTO reservation, MultipartFile file);

	List<ReservationDTO> findAll(int page);

	ReservationDTO findById(Long reservationNo);

	void delete(Long reservationNo);
	
	List<ReservationDTO> search(String keyword, String condition, int page);

}
