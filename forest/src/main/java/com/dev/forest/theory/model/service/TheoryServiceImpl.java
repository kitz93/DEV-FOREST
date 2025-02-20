package com.dev.forest.theory.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.forest.theory.model.dto.TheoryDTO;
import com.dev.forest.theory.model.mapper.TheoryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TheoryServiceImpl implements TheoryService {
	
	private final TheoryMapper mapper;
	
	@Override
	public List<TheoryDTO> getAllTheorys() {
		return mapper.getAllTheorys();
	}

	@Override
	public TheoryDTO getTheoryById(Long id) {
		return mapper.getTheoryById(id);
	}

	

}
