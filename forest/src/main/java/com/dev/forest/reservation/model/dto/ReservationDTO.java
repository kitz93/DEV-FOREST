package com.dev.forest.reservation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReservationDTO {
	
	private Long reservationNo;
	private String reservationUser;
	private int reservationCount;
	private String reservationName;
	private String reservationContent;
	private String reservationPlace;
	private String reservationAddress;
	private String reservationStatus;
	private String startTime;
	private String endTime;
	private String reservationDate;
	private String fileUrl;
	
}
