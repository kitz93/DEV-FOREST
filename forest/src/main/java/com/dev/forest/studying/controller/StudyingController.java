package com.dev.forest.studying.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.forest.studying.model.dto.StudyingDTO;
import com.dev.forest.studying.model.service.StudyingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("studyings")
public class StudyingController {
	
	private final StudyingService studyingService;
	
	@PostMapping
	public ResponseEntity<?> attend(@ModelAttribute @Valid StudyingDTO studying) {
		
		studyingService.attend(studying);
		return ResponseEntity.status(HttpStatus.CREATED).body("모임에 참석되었습니다!"); 
	}
	
	@GetMapping("/{reservationNo}")
	public ResponseEntity<List<StudyingDTO>> findByReservationNO(@PathVariable(name = "reservationNo") Long reservationNo){
		
		List<StudyingDTO> list = studyingService.findByRervationNo(reservationNo);
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/{reservationNo}")
	public ResponseEntity<?> cancle(@PathVariable(name = "reservationNo") Long reservationNo){
		studyingService.cancle(reservationNo);
		return ResponseEntity.ok("모임 참석이 취소되었습니다!");
	}
	
	

}
