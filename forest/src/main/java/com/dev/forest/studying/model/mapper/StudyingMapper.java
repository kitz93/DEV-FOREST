package com.dev.forest.studying.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.studying.model.dto.StudyingDTO;

@Mapper
public interface StudyingMapper {

	void attend(StudyingDTO studying);

	List<StudyingDTO> findByRervationNo(Long refBno);

	void cancle(Long refBno);

	int countByReservationNo(Long refBno);

}
