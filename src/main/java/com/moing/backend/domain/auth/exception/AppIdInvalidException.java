package com.moing.backend.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class AppIdInvalidException extends AuthException {
	public AppIdInvalidException() {
		super(ErrorCode.APPID_INVALID_ERROR,
			HttpStatus.CONFLICT);
	}
}
