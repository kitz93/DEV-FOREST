package com.dev.forest.progress.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.forest.progress.model.dto.ProgressDTO;
import com.dev.forest.progress.model.mapper.ProgressMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgressServiceImpl implements ProgressService {
	
	private final ProgressMapper progressMapper;

	@Override
	public List<ProgressDTO> getAllProgress() {
		return null;
	}



}
