package com.dev.forest.reservation.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.forest.reservation.model.dto.ReservationDTO;
import com.dev.forest.reservation.model.service.ReservationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("reservations")
public class ReservationController {
	
	private final ReservationService reservationService;
	
	@PostMapping
	public ResponseEntity<?> reservate(@ModelAttribute @Valid ReservationDTO reservation,
									   @RequestParam(name = "file", required = false) MultipartFile file) {
		reservationService.reservate(reservation, file);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("모임 예약 성공");
	}
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> findAll(@RequestParam(name = "page", defaultValue = "0") int page) {
		return ResponseEntity.ok(reservationService.findAll(page));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReservationDTO> findById(@PathVariable(name = "id") @Min(value = 1, message = "0보다 작은 수는 없습니다.") Long reservationNo){
		return ResponseEntity.ok(reservationService.findById(reservationNo));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long reservationNo){
		reservationService.delete(reservationNo);
		return ResponseEntity.ok("성공적으로 삭제되었습니다.");
	}
	
	@GetMapping("/search")
	public ResponseEntity<Map<String, Object>> search(@RequestParam(name = "page", defaultValue = "0")int page,
													   @RequestParam(name = "condition") String condition,									       
													   @RequestParam(name = "keyword") String keyword) {
		
		return ResponseEntity.ok(reservationService.search(keyword,condition,page));
		
	}

}
