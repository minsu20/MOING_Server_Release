package com.moing.backend.domain.fire.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NoAuthThrowFireException extends FireException {

	public NoAuthThrowFireException() {
		super(ErrorCode.NOT_AUTH_FIRE_THROW,
			HttpStatus.NOT_FOUND);
	}
}
