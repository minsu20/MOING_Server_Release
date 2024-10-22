package com.moing.backend.global.config.fcm.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class InitializeException extends FirebaseException {
	public InitializeException() {
		super(ErrorCode.INITIALIZE_ERROR,
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
}
