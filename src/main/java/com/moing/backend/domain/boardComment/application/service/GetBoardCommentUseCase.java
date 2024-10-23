package com.moing.backend.domain.boardComment.application.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.moing.backend.domain.boardComment.domain.service.BoardCommentGetService;
import com.moing.backend.domain.comment.application.dto.response.GetCommentResponse;
import com.moing.backend.global.response.BaseBoardServiceResponse;
import com.moing.backend.global.service.BaseBoardService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class GetBoardCommentUseCase {

	private final BoardCommentGetService boardCommentGetService;
	private final BaseBoardService baseBoardService;

	/**
	 * 게시글 댓글 전체 조회
	 */
	public GetCommentResponse getBoardCommentAll(String socialId, Long teamId, Long boardId) {
		BaseBoardServiceResponse data = baseBoardService.getCommonData(socialId, teamId, boardId);
		return boardCommentGetService.getCommentAll(boardId, data.getTeamMember());
	}
}
