package com.dev.forest.reply.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.forest.auth.model.service.AuthenticationService;
import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.board.model.service.BoardService;
import com.dev.forest.reply.model.dto.ReplyDTO;
import com.dev.forest.reply.model.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyServiceImpl implements ReplyService {

	private final ReplyMapper replyMapper;
	private final BoardService boardService;
	private final AuthenticationService authService;

	@Override
	public void saveReply(ReplyDTO reply) {
		// 게시글 존재여부 확인
		boardService.findById(reply.getRefBno());

		// 검증된 유저인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		log.info("게시글 작성자 : {}",reply.getReplyWriter());
		log.info("로그인 유저 : {}",user.getNickname());
		authService.validWriter(reply.getReplyWriter(), user.getNickname());
		
		reply.setReplyWriter(String.valueOf(user.getUserNo()));

		// 댓글 작성
		replyMapper.saveReply(reply);
	}

	@Override
	public List<ReplyDTO> findByBoardNo(Long boardNo) {
		return replyMapper.findByBoardNo(boardNo);
	}

}
