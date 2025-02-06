package com.dev.forest.reply.model.service;

import java.util.List;

import com.dev.forest.reply.model.dto.ReplyDTO;

public interface ReplyService {

	void saveReply(ReplyDTO reply);

	List<ReplyDTO> findByBoardNo(Long boardNo);

}
