package com.dev.forest.reply.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.forest.auth.model.service.AuthenticationService;
import com.dev.forest.auth.model.vo.CustomUserDetails;
import com.dev.forest.board.model.mapper.BoardMapper;
import com.dev.forest.exception.BoardNotFoundException;
import com.dev.forest.reply.model.dto.ReplyDTO;
import com.dev.forest.reply.model.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyServiceImpl implements ReplyService {

	private final ReplyMapper replyMapper;
	private final BoardMapper boardMapper;
	private final AuthenticationService authService;

	@Override
	@Transactional
	public void saveReply(ReplyDTO reply) {
		// 게시글 존재여부 확인 (조회수를 올리지 않기 위해 findById 대신 존재 여부만 확인)
		if (boardMapper.existsById(reply.getRefBno()) == 0) {
			throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
		}

		// 검증된 유저인지 확인
		CustomUserDetails user = authService.getAuthenticatedUser();
		reply.setReplyWriter(String.valueOf(user.getUserNo()));

		// 댓글 작성
		replyMapper.saveReply(reply);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReplyDTO> findByBoardNo(Long boardNo) {
		return replyMapper.findByBoardNo(boardNo);
	}

}
