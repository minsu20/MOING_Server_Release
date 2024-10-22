package com.moing.backend.domain.boardComment.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.boardComment.domain.entity.BoardComment;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long>, BoardCommentCustomRepository {
	Optional<BoardComment> findBoardCommentByBoardCommentId(Long boardCommentId);
}
