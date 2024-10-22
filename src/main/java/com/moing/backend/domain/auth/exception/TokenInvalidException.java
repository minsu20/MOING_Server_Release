package com.moing.backend.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class TokenInvalidException extends AuthException {
	public TokenInvalidException() {
		super(ErrorCode.TOKEN_INVALID_ERROR,
			HttpStatus.CONFLICT);
	}
}
