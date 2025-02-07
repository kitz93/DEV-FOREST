package com.dev.forest.exception;

public class AccessTokenExpiredException extends RuntimeException {
	
	public AccessTokenExpiredException(String message) {
		super(message);
	}

}
