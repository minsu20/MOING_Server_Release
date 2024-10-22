package com.moing.backend.domain.team.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class AlreadyJoinTeamException extends TeamException {
	public AlreadyJoinTeamException() {
		super(ErrorCode.ALREADY_JOIN_ERROR,
			HttpStatus.UNAUTHORIZED);
	}
}
