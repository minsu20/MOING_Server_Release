package com.moing.backend.domain.mission.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NoAccessUpdateMission extends MissionException {

	public NoAccessUpdateMission() {
		super(ErrorCode.NO_ACCESS_UPDATE_MISSION,
			HttpStatus.UNAUTHORIZED);
	}
}
