package com.moing.backend.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.exception.ApplicationException;
import com.moing.backend.global.response.ErrorCode;

public abstract class AuthException extends ApplicationException {
	protected AuthException(ErrorCode errorCode, HttpStatus httpStatus) {
		super(errorCode, httpStatus);
	}
}
