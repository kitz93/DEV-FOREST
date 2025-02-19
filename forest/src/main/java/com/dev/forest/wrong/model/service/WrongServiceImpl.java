package com.dev.forest.wrong.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.forest.wrong.model.dto.WrongDTO;
import com.dev.forest.wrong.model.mapper.WrongMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WrongServiceImpl implements WrongService {
	
	private final WrongMapper wrongMapper;
	
	@Override
	public void insertWrong(WrongDTO wrong) {
		log.info("오답 정보 : {}", wrong);
		wrongMapper.insertWrong(wrong);
	}

	@Override
	public List<WrongDTO> findAll() {
		return wrongMapper.findAll();
	}
	
	@Override
	public List<WrongDTO> findById(Long userNo) {
		return wrongMapper.findById(userNo);
	}

}
