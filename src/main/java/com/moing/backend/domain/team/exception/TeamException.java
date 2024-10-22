package com.moing.backend.domain.team.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.exception.ApplicationException;
import com.moing.backend.global.response.ErrorCode;

public abstract class TeamException extends ApplicationException {
	protected TeamException(ErrorCode errorCode, HttpStatus httpStatus) {
		super(errorCode, httpStatus);
	}
}
