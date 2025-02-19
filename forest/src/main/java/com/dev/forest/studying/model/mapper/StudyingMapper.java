package com.dev.forest.studying.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.studying.model.dto.StudyingDTO;

@Mapper
public interface StudyingMapper {

	void attend(StudyingDTO studying);

	List<StudyingDTO> findByRervationNo(Long refBno);

	void cancle(Map<String, Object> params);

	int countByReservationNo(Long refBno);

}
