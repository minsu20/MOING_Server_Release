package com.moing.backend.domain.boardRead.application.mapper;

import org.springframework.stereotype.Component;

import com.moing.backend.domain.boardRead.domain.entity.BoardRead;
import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.team.domain.entity.Team;

@Component
public class BoardReadMapper {
	public static BoardRead toBoardRead(Team team, Member member) {
		BoardRead boardRead = new BoardRead();
		boardRead.updateTeam(team);
		boardRead.updateMember(member);
		return boardRead;
	}
}
