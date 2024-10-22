package com.moing.backend.domain.missionArchive.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NotYetMissionArchiveException extends MissionArchiveException {

	public NotYetMissionArchiveException() {
		super(ErrorCode.NOT_YET_MISSION_ARCHIVE,
			HttpStatus.NOT_FOUND);
	}
}
