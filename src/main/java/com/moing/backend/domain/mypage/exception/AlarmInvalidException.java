package com.moing.backend.domain.mypage.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class AlarmInvalidException extends MyPageException {
	public AlarmInvalidException() {
		super(ErrorCode.INVALID_ALARM_ERROR,
			HttpStatus.NOT_FOUND);
	}
}
