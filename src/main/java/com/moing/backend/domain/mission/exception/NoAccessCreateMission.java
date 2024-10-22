package com.moing.backend.domain.mission.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NoAccessCreateMission extends MissionException {

	public NoAccessCreateMission() {
		super(ErrorCode.NO_ACCESS_CREATE_MISSION,
			HttpStatus.UNAUTHORIZED);
	}
}
