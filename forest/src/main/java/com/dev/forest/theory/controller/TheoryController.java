package com.dev.forest.theory.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.forest.theory.model.dto.TheoryDTO;
import com.dev.forest.theory.model.service.TheoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("theorys")
@Slf4j
public class TheoryController {
	
	private final TheoryService service;
	
	@GetMapping
	public List<TheoryDTO> getAllTheorys() {
		return service.getAllTheorys();
	}
	
	@GetMapping("/{id}")
	public TheoryDTO getTheoryById(@PathVariable("id") Long id) {
		return service.getTheoryById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
