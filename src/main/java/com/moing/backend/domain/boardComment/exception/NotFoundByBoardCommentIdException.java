package com.moing.backend.domain.boardComment.exception;

import org.springframework.http.HttpStatus;

import com.moing.backend.global.response.ErrorCode;

public class NotFoundByBoardCommentIdException extends BoardCommentException {
	public NotFoundByBoardCommentIdException() {
		super(ErrorCode.NOT_FOUND_BY_BOARD_COMMENT_ID_ERROR,
			HttpStatus.NOT_FOUND);
	}
}
