package com.dev.forest.studying.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dev.forest.studying.model.dto.StudyingDTO;

@Mapper
public interface StudyingMapper {

	void attend(StudyingDTO studying);

	List<StudyingDTO> findByReservationNo(Long refBno);

	void cancel(Map<String, Object> params);

	int countByReservationNo(Long refBno);

	int existsAttendee(@Param("refRno") Long refRno, @Param("userNo") Long userNo);

}
