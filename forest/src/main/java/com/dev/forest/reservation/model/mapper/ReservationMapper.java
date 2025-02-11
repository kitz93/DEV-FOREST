package com.dev.forest.reservation.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.dev.forest.reservation.model.dto.ReservationDTO;

@Mapper
public interface ReservationMapper {

	void reservate(ReservationDTO reservation);

	int selectTotalCount();

	List<ReservationDTO> findAll(RowBounds rowBounds);
	
	ReservationDTO findById(Long reservationNo);

	void delete(Long reservationNo);

	int searchCount(String keyword, String condition);

	List<ReservationDTO> search(String keyword, String condition, RowBounds rowBounds);

	void attend(ReservationDTO reservation);

}
