package com.moing.backend.domain.mypage.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.exception.ApplicationException;
import com.moing.backend.global.response.ErrorCode;

public class ExistingTeamException extends ApplicationException {
	public ExistingTeamException() {
		super(ErrorCode.EXISTING_TEAM_ERROR,
			HttpStatus.NOT_FOUND);
	}
}
