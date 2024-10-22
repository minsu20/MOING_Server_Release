package com.moing.backend.domain.block.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.exception.ApplicationException;
import com.moing.backend.global.response.ErrorCode;

public abstract class BlockException extends ApplicationException {
	protected BlockException(ErrorCode errorCode, HttpStatus httpStatus) {
		super(errorCode, httpStatus);
	}
}
