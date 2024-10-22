package com.moing.backend.domain.mission.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NoMoreCreateMission extends MissionException {

	public NoMoreCreateMission() {
		super(ErrorCode.NO_MORE_CREATE_MISSION,
			HttpStatus.UNAUTHORIZED);
	}
}
