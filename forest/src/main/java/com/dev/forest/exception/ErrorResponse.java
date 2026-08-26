package com.dev.forest.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

public record ErrorResponse(int status, String code, String message, LocalDateTime timestamp,
		Map<String, String> fieldErrors) {

	public static ErrorResponse of(HttpStatus status, String code, String message) {
		return new ErrorResponse(status.value(), code, message, LocalDateTime.now(), null);
	}

	public static ErrorResponse of(HttpStatus status, String code, String message, Map<String, String> fieldErrors) {
		return new ErrorResponse(status.value(), code, message, LocalDateTime.now(), fieldErrors);
	}

}
