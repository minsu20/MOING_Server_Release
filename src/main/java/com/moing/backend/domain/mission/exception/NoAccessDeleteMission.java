package com.moing.backend.domain.mission.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NoAccessDeleteMission extends MissionException {

	public NoAccessDeleteMission() {
		super(ErrorCode.NO_ACCESS_DELETE_MISSION,
			HttpStatus.UNAUTHORIZED);
	}
}
