package com.moing.backend.domain.board.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NotAuthByBoardException extends BoardException {
	public NotAuthByBoardException() {
		super(ErrorCode.NOT_AUTH_BY_BOARD_ID_ERROR,
			HttpStatus.NOT_FOUND);
	}
}
