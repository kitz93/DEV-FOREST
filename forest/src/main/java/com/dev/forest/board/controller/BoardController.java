package com.dev.forest.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.forest.board.model.dto.BoardDTO;
import com.dev.forest.board.model.service.BoardService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("boards")
public class BoardController {
	
	private final BoardService service;
	
	@PostMapping
	public ResponseEntity<?> save(@ModelAttribute @Valid BoardDTO board,
								  @RequestParam(name = "boardType") int boardType,
								  @RequestParam(name = "file", required = false) MultipartFile file ) {
		
		service.save(board,boardType, file);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("게시글 등록 성공");
	}
	
	@GetMapping
	public ResponseEntity<List<BoardDTO>> findAll(@RequestParam(name = "page", defaultValue = "1") int page,
												  @RequestParam(name = "boardType") int boardType ){
		
		return ResponseEntity.ok(service.findAll(boardType, page));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BoardDTO> findById(@PathVariable( name = "id")
											 @Min(value = 1, message = "0보다 작은 수 입니다.") Long boardNo){
		
		return ResponseEntity.ok(service.findById(boardNo));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BoardDTO> update(@PathVariable(name = "id") Long boardNo,@ModelAttribute @Valid BoardDTO board, @RequestParam(name = "file") MultipartFile file){
		board.setBoardNo(boardNo);
		BoardDTO updated = service.update(board, file);
		return ResponseEntity.ok(updated);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long boardNo) {
		service.delete(boardNo);
		return ResponseEntity.ok("삭제가 완료되었습니다.");
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<BoardDTO>> search(@RequestParam(name = "boardType") int boardType, 
												 @RequestParam(name = "condition") String condition, 
												 @RequestParam(name = "keyword") String keyword, 
												 @RequestParam(name = "page", defaultValue = "1") int page){
		return ResponseEntity.ok(service.search(boardType, condition, keyword,page));
	}

}
