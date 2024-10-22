package com.moing.backend.domain.fire.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.exception.ApplicationException;
import com.moing.backend.global.response.ErrorCode;

public abstract class FireException extends ApplicationException {

	protected FireException(ErrorCode errorCode, HttpStatus httpStatus) {
		super(errorCode, httpStatus);
	}

}
