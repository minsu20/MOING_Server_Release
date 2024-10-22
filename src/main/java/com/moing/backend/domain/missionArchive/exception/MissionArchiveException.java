package com.moing.backend.domain.missionArchive.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.exception.ApplicationException;
import com.moing.backend.global.response.ErrorCode;

public abstract class MissionArchiveException extends ApplicationException {

	protected MissionArchiveException(ErrorCode errorCode, HttpStatus httpStatus) {
		super(errorCode, httpStatus);
	}

}
