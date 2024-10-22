package com.moing.backend.domain.board.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NotFoundByBoardIdException extends BoardException {
	public NotFoundByBoardIdException() {
		super(ErrorCode.NOT_FOUND_BY_BOARD_ID_ERROR,
			HttpStatus.NOT_FOUND);
	}
}
