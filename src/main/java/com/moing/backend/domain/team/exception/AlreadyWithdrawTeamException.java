package com.moing.backend.domain.team.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class AlreadyWithdrawTeamException extends TeamException {
	public AlreadyWithdrawTeamException() {
		super(ErrorCode.ALREADY_WITHDRAW_ERROR,
			HttpStatus.NOT_FOUND);
	}
}
