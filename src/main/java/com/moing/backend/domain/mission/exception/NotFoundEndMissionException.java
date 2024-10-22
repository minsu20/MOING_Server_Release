package com.moing.backend.domain.mission.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NotFoundEndMissionException extends MissionException {

	public NotFoundEndMissionException() {
		super(ErrorCode.NOT_FOUND_MISSION,
			HttpStatus.NOT_FOUND);
	}
}
