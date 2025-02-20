package com.dev.forest.progress.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.forest.progress.model.dto.ProgressDTO;
import com.dev.forest.progress.model.service.ProgressService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("progress")
@Slf4j
public class ProgressController {
	
	private final ProgressService progressService;
	
	@GetMapping
	public List<ProgressDTO> getAllProgress() {
		return progressService.getAllProgress();
	}
	

}
