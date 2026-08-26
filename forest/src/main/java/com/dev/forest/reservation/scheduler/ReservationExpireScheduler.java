package com.dev.forest.reservation.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dev.forest.reservation.model.mapper.ReservationMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReservationExpireScheduler {

	private final ReservationMapper reservationMapper;

	@Scheduled(fixedDelay = 60_000)
	@Transactional
	public void expireReservations() {
		int expiredCount = reservationMapper.expireAll();
		if (expiredCount > 0) {
			log.info("만료 처리된 모임 수 : {}", expiredCount);
		}
	}

}
