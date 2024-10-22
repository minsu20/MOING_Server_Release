package com.moing.backend.global.config.fcm.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class MessagingException extends FirebaseException {
	public MessagingException(String message) {
		super(ErrorCode.MESSAGING_ERROR,
			HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
