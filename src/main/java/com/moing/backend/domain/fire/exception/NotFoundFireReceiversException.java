package com.moing.backend.domain.fire.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NotFoundFireReceiversException extends FireException {

	public NotFoundFireReceiversException() {
		super(ErrorCode.NOT_FOUND_FIRE_RECEIVERS,
			HttpStatus.NOT_FOUND);
	}
}
