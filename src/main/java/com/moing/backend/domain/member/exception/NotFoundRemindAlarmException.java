package com.moing.backend.domain.member.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NotFoundRemindAlarmException extends MemberException {
	public NotFoundRemindAlarmException() {
		super(ErrorCode.NOT_FOUND_ALL_MEMBER,
			HttpStatus.NOT_FOUND);
	}
}
