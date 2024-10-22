package com.moing.backend.domain.boardRead.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import com.moing.backend.domain.board.domain.entity.Board;
import com.moing.backend.domain.boardRead.domain.entity.BoardRead;
import com.moing.backend.domain.boardRead.domain.repository.BoardReadRepository;
import com.moing.backend.global.annotation.DomainService;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
@Transactional
public class BoardReadSaveService {

	private final BoardReadRepository boardReadRepository;

	public void saveBoardRead(Board board, BoardRead boardRead) {
		List<BoardRead> existingBoardReads = boardReadRepository.findBoardReadByBoardAndMemberAndTeam(board,
			boardRead.getMember(), boardRead.getTeam());

		if (existingBoardReads.isEmpty()) {
			boardRead.updateBoard(board);
			boardReadRepository.save(boardRead);
		}
	}
}
