package com.dev.forest.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	// 404
	@ExceptionHandler({ BoardNotFoundException.class, ReservationNotFoundException.class,
			UserNotFoundException.class })
	public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException e) {
		return build(HttpStatus.NOT_FOUND, e);
	}

	// 409
	@ExceptionHandler({ PullCountStudyingException.class, DuplicateAttendException.class,
			DuplicatedUserException.class })
	public ResponseEntity<ErrorResponse> handleConflict(RuntimeException e) {
		return build(HttpStatus.CONFLICT, e);
	}

	// 400
	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<ErrorResponse> handleInvalidParameter(InvalidParameterException e) {
		return build(HttpStatus.BAD_REQUEST, e);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleArgumentValid(MethodArgumentNotValidException e) {
		Map<String, String> fieldErrors = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getClass().getSimpleName(), "요청값이 올바르지 않습니다.", fieldErrors));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException e) {
		Map<String, String> fieldErrors = new HashMap<>();
		e.getConstraintViolations().forEach(v -> fieldErrors.put(v.getPropertyPath().toString(), v.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorResponse.of(HttpStatus.BAD_REQUEST, e.getClass().getSimpleName(), "요청값이 올바르지 않습니다.", fieldErrors));
	}

	// 401
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleAuthentication(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ErrorResponse.of(HttpStatus.UNAUTHORIZED, e.getClass().getSimpleName(), "아이디 및 비밀번호 오류"));
	}

	@ExceptionHandler({ AccessTokenExpiredException.class, JwtTokenException.class, MismatchPasswordException.class })
	public ResponseEntity<ErrorResponse> handleUnauthorized(RuntimeException e) {
		return build(HttpStatus.UNAUTHORIZED, e);
	}

	// 403
	@ExceptionHandler({ AccessDeniedException.class, DeleteMemberException.class })
	public ResponseEntity<ErrorResponse> handleForbidden(RuntimeException e) {
		return build(HttpStatus.FORBIDDEN, e);
	}

	// 500
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleUnexpected(Exception e) {
		log.error("처리되지 않은 예외", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "InternalServerError", "서버 오류가 발생했습니다."));
	}

	private ResponseEntity<ErrorResponse> build(HttpStatus status, RuntimeException e) {
		return ResponseEntity.status(status).body(ErrorResponse.of(status, e.getClass().getSimpleName(), e.getMessage()));
	}

}
