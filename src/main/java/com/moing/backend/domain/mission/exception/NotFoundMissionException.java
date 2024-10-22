package com.moing.backend.domain.mission.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NotFoundMissionException extends MissionException {

	public NotFoundMissionException() {
		super(ErrorCode.NOT_FOUND_MISSION,
			HttpStatus.NOT_FOUND);
	}
}
