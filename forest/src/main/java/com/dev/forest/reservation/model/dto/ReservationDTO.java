package com.dev.forest.reservation.model.dto;

import java.time.LocalDateTime;

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

	// 화면 표시용 닉네임. 작성자 판별에는 쓰지 않는다(principal 기준 hostNo 사용).
	private String reservationUser;
	private Long hostNo;

	@NotBlank(message = "모임명이 비어있으면 안됩니다.")
	private String reservationName;
	
	@NotBlank(message = "모임에 대한 설명이 비어있으면 안됩니다.")
	private String reservationContent;
	private int reservationCount;
	
	@NotBlank(message = "모임장소를 고르셔야 합니다.")
	private String reservationPlace;
	private String placeAddress;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String reservationStatus;
	private String status;
	private String fileUrl;
	private int currentMembers;
	
}
