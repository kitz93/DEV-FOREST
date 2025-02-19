package com.dev.forest.ranking.model.service;

import java.util.List;

import com.dev.forest.ranking.model.dto.RankingDTO;

public interface RankingService {

	void insertRanking(RankingDTO ranking);

	List<RankingDTO> getRanking();




}
