package com.dev.forest.ranking.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.forest.ranking.model.dto.RankingDTO;
import com.dev.forest.ranking.model.mapper.RankingMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RankingServiceImpl implements RankingService {
	
	private final RankingMapper rankingMapper;
	
	@Override
	public void insertRanking(RankingDTO ranking) {
		log.info("랭킹 정보 : {}", ranking);
	}

	@Override
	public List<RankingDTO> getRanking() {
		List<RankingDTO> list = rankingMapper.getRanking();
		return list;
	}


}
