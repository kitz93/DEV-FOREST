package com.dev.forest.wrong.model.service;

import java.util.List;

import com.dev.forest.wrong.model.dto.WrongDTO;

import jakarta.validation.constraints.Min;

public interface WrongService {

	void insertWrong(WrongDTO wrong);

	List<WrongDTO> findAll();

	List<WrongDTO> findById(Long userNo);

}
