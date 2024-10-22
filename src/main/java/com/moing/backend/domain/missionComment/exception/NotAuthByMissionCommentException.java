package com.moing.backend.domain.missionComment.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NotAuthByMissionCommentException extends MissionCommentException {
	public NotAuthByMissionCommentException() {
		super(ErrorCode.NOT_AUTH_BY_MISSION_COMMENT_ID_ERROR,
			HttpStatus.NOT_FOUND);
	}
}
