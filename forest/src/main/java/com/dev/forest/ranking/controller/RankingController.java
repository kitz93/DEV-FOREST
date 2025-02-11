package com.dev.forest.ranking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.forest.ranking.model.dto.RankingDTO;
import com.dev.forest.ranking.model.service.RankingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("rankings")
@Slf4j
public class RankingController {
	
	private final RankingService rankingService;
	
	@PostMapping
	public ResponseEntity<?> insertRanking(@RequestBody @Valid RankingDTO ranking) {
		log.info("랭킹 입력값 : {}", ranking);
		return ResponseEntity.status(HttpStatus.CREATED).body("");
	}
	
	@GetMapping()
	public ResponseEntity<List<RankingDTO>> getRanking() {
		log.info("여기로 오냐");
		List<RankingDTO> list = rankingService.getRanking();
		return ResponseEntity.ok(list);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
