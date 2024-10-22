package com.moing.backend.domain.missionHeart.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NoAccessMissionHeartException extends MissionHeartException {
	public NoAccessMissionHeartException() {
		super(ErrorCode.NO_ACCESS_HEART_FOR_ME, HttpStatus.NOT_FOUND);

	}
}
