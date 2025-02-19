package com.dev.forest.reply.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.forest.reply.model.dto.ReplyDTO;
import com.dev.forest.reply.model.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("replys")
public class ReplyController {
	
	private final ReplyService replyService;
	
	@PostMapping
	public ResponseEntity<?> saveReply(ReplyDTO reply){
		replyService.saveReply(reply);
		return ResponseEntity.status(HttpStatus.CREATED).body("댓글 작성을 성공했습니다.");
	}
	
	@GetMapping("/{boardNo}")
	public ResponseEntity<List<ReplyDTO>> findByBoardNo(@PathVariable(name = "boardNo") Long boardNo){
		List<ReplyDTO> replys = replyService.findByBoardNo(boardNo);
		return ResponseEntity.ok(replys);
		
	}

}
