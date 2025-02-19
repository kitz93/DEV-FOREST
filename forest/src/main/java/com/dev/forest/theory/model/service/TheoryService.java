package com.dev.forest.theory.model.service;

import java.util.List;

import com.dev.forest.theory.model.dto.TheoryDTO;

public interface TheoryService {

	List<TheoryDTO> getAllTheorys();

	TheoryDTO getTheoryById(Long id);

}
