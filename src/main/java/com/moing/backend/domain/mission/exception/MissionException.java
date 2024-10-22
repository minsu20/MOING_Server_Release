package com.moing.backend.domain.mission.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.exception.ApplicationException;
import com.moing.backend.global.response.ErrorCode;

public abstract class MissionException extends ApplicationException {

	protected MissionException(ErrorCode errorCode, HttpStatus httpStatus) {
		super(errorCode, httpStatus);
	}

}
