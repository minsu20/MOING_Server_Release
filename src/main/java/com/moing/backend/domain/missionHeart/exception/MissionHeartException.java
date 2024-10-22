package com.moing.backend.domain.missionHeart.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.exception.ApplicationException;
import com.moing.backend.global.response.ErrorCode;

public abstract class MissionHeartException extends ApplicationException {
	protected MissionHeartException(ErrorCode errorCode, HttpStatus httpStatus) {
		super(errorCode, httpStatus);
	}
}
