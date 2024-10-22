package com.moing.backend.domain.missionArchive.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NoAccessMissionArchiveException extends MissionArchiveException {
	public NoAccessMissionArchiveException() {
		super(ErrorCode.NO_MORE_ARCHIVE_ERROR, HttpStatus.NOT_FOUND);

	}
}
