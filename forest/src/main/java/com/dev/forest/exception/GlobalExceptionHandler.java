package com.dev.forest.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
	
	@ExceptionHandler(BoardNotFoundException.class)
	public ResponseEntity<?> handleBoardNotFound(BoardNotFoundException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<?> handleInvalidParameter(InvalidParameterException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	

	
}
