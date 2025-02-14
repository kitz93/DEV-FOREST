package com.dev.forest.progress.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.progress.model.dto.ProgressDTO;

@Mapper
public interface ProgressMapper {

	List<ProgressDTO> getAllProgress();


}
