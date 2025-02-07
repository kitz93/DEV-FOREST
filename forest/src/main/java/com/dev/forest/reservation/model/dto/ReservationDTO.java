package com.dev.forest.reservation.model.dto;

import jakarta.validation.constraints.NotBlank;
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
	
	@NotBlank(message = "모임명이 비어있으면 안됩니다.")
	private String reservationName;
	
	@NotBlank(message = "모임에 대한 설명이 비어있으면 안됩니다.")
	private String reservationContent;
	
	@NotBlank(message = "모임장소를 고르셔야 합니다.")
	private String reservationPlace;
	private String reservationAddress;
	private String reservationStatus;
	private String status;
	private String startTime;
	private String endTime;
	private String reservationDate;
	private String fileUrl;
	
}
