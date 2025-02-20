package com.dev.forest.wrong.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.forest.wrong.model.dto.WrongDTO;
import com.dev.forest.wrong.model.service.WrongService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("wrongs")
@Slf4j
public class WrongController {
	
	private final WrongService wrongService;
	
	@PostMapping
	public ResponseEntity<?> insertWrong(@RequestBody @Valid WrongDTO wrong) {
		// log.info("오답 입력값 : {}", wrong);
		wrongService.insertWrong(wrong);
		return ResponseEntity.status(HttpStatus.CREATED).body("오답 등록 성공");
	}
	
	@GetMapping
	public ResponseEntity<List<WrongDTO>> findAll() {
		List<WrongDTO> wrongDTO = wrongService.findAll();
		return ResponseEntity.ok(wrongDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<WrongDTO>> findById(@PathVariable(name="id") @Min(value=1, message="1보다 큰 수를 입력해주세요.") Long userNo) {
		List<WrongDTO> wrongDTO = wrongService.findById(userNo);
		return ResponseEntity.ok(wrongDTO);
	}
	

}
