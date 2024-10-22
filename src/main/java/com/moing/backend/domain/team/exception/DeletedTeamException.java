package com.moing.backend.domain.team.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class DeletedTeamException extends TeamException {
	public DeletedTeamException() {
		super(ErrorCode.DELETED_TEAM_ERROR,
			HttpStatus.NOT_FOUND);
	}
}
