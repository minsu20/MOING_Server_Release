package com.moing.backend.domain.fire.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NotFoundFireException extends FireException {

	public NotFoundFireException() {
		super(ErrorCode.NOT_FOUND_FIRE,
			HttpStatus.NOT_FOUND);
	}
}
