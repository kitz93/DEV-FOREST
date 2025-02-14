package com.dev.forest.ranking.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.ranking.model.dto.RankingDTO;

@Mapper
public interface RankingMapper {

	List<RankingDTO> getRanking();

}
