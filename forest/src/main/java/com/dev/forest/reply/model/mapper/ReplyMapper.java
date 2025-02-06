package com.dev.forest.reply.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dev.forest.reply.model.dto.ReplyDTO;

@Mapper
public interface ReplyMapper {

	void saveReply(ReplyDTO reply);

	List<ReplyDTO> findByBoardNo(Long boardNo);

}
