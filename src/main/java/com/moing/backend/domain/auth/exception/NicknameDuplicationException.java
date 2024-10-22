package com.moing.backend.domain.auth.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NicknameDuplicationException extends AuthException {
	public NicknameDuplicationException() {
		super(ErrorCode.NICKNAME_DUPLICATION_ERROR,
			HttpStatus.BAD_REQUEST);
	}
}
