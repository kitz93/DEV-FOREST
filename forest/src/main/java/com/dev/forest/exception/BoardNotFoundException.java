package com.dev.forest.exception;

public class BoardNotFoundException extends RuntimeException{
	public BoardNotFoundException(String message) {
		super(message);
	}
}
