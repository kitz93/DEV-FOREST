package com.dev.forest.exception;

public class DupplicatedUserException extends RuntimeException {
	
	public DupplicatedUserException(String message) {
		super(message);
	}

}
