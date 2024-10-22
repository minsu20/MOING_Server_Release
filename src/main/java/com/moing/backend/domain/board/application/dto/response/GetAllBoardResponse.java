package com.moing.backend.domain.board.application.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class GetAllBoardResponse {
	private int noticeNum;
	private List<BoardBlocks> noticeBlocks = new ArrayList<>();
	private int notNoticeNum;
	private List<BoardBlocks> notNoticeBlocks = new ArrayList<>();
}
