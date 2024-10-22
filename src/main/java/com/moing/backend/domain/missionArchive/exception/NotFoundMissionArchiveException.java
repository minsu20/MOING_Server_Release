package com.moing.backend.domain.missionArchive.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NotFoundMissionArchiveException extends MissionArchiveException {

	public NotFoundMissionArchiveException() {
		super(ErrorCode.NOT_FOUND_MISSION_ARCHIVE,
			HttpStatus.NOT_FOUND);
	}
}
