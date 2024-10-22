package com.moing.backend.domain.board.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.exception.ApplicationException;
import com.moing.backend.global.response.ErrorCode;

public abstract class BoardException extends ApplicationException {
	protected BoardException(ErrorCode errorCode, HttpStatus httpStatus) {
		super(errorCode, httpStatus);
	}
}
