package com.moing.backend.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class AccountAlreadyExistedException extends AuthException {
	public AccountAlreadyExistedException() {
		super(ErrorCode.ACCOUNT_ALREADY_EXIST,
			HttpStatus.UNAUTHORIZED);
	}
}

