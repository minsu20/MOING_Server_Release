package com.moing.backend.domain.board.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.board.domain.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {

	Optional<Board> findBoardByBoardId(Long boardId);
}
