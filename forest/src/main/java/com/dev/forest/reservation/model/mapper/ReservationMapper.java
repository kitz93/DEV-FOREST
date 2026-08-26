package com.dev.forest.reservation.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dev.forest.reservation.model.dto.ReservationDTO;

@Mapper
public interface ReservationMapper {

	void reservate(ReservationDTO reservation);

	int selectTotalCount();

	List<ReservationDTO> findAll(@Param("offset") int offset, @Param("limit") int limit);

	ReservationDTO findById(Long reservationNo);

	void delete(ReservationDTO exsitingReservation);

	int searchCount(Map<String, Object> params);

	List<ReservationDTO> search(Map<String, Object> params);

	int getMaxCount(Long reservationNo);

	Integer getMaxCountForUpdate(Long reservationNo);

	void pullReservationStatus(Long reservationNo);

	void notPullReservationStatus(Long reservationNo);

	int expireAll();

}
