package com.moing.backend.global.exception;

import java.util.function.Consumer;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.moing.backend.global.config.slack.exception.dto.ExceptionEvent;
import com.moing.backend.global.response.ErrorCode;
import com.moing.backend.global.response.ErrorResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	private static final String LOG_FORMAT = "Class : {}, Code : {}, Message : {}";
	private final ApplicationEventPublisher eventPublisher;

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException ex) {
		return handleException(ex, ex.getErrorCode(), ex.getMessage(), ex.getHttpStatus(), log::warn);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> inputMethodArgumentInvalidExceptionHandler(
		MethodArgumentNotValidException ex) {
		String message = ex.getBindingResult().getFieldErrors().stream()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.collect(Collectors.joining(", "));
		return handleException(ex, ErrorCode.BAD_REQUEST, message, HttpStatus.BAD_REQUEST, log::warn);
	}

	@ExceptionHandler(PatternSyntaxException.class)
	public ResponseEntity<ErrorResponse> inputPatternSyntaxExceptionHandler(PatternSyntaxException ex) {
		return handleException(ex, ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage(), HttpStatus.BAD_REQUEST,
			log::warn);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> jsonParseExceptionHandler(HttpMessageNotReadableException ex) {
		return handleException(ex, ErrorCode.BAD_REQUEST, ErrorCode.BAD_REQUEST.getMessage(), HttpStatus.BAD_REQUEST,
			log::warn);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> httpRequestNotSupportedExceptionHandler(
		HttpRequestMethodNotSupportedException ex) {
		return handleException(ex, ErrorCode.METHOD_NOT_ALLOWED, ErrorCode.METHOD_NOT_ALLOWED.getMessage(),
			HttpStatus.METHOD_NOT_ALLOWED, log::warn);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> internalServerErrorHandler(Exception ex, HttpServletRequest request) {
		eventPublisher.publishEvent(new ExceptionEvent(request, ex));
		return handleException(ex, ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
			HttpStatus.INTERNAL_SERVER_ERROR, log::error);
	}

	private ResponseEntity<ErrorResponse> handleException(Exception ex, ErrorCode errorCode, String message,
		HttpStatus httpStatus, Consumer<String> logger) {
		log.error(LOG_FORMAT, ex.getClass().getSimpleName(), errorCode.getErrorCode(), ex.getMessage());
		ErrorResponse errorResponse = new ErrorResponse(errorCode, message);
		return ResponseEntity.status(httpStatus.value()).body(errorResponse);
	}

}
