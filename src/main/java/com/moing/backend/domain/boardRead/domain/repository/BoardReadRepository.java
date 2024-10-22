package com.moing.backend.domain.boardRead.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.board.domain.entity.Board;
import com.moing.backend.domain.boardRead.domain.entity.BoardRead;
import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.team.domain.entity.Team;

public interface BoardReadRepository extends JpaRepository<BoardRead, Long> {

	List<BoardRead> findBoardReadByBoardAndMemberAndTeam(Board board, Member member, Team team);
}
