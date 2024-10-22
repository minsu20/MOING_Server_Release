package com.moing.backend.domain.teamScore.application.mapper;

import org.springframework.stereotype.Component;

import com.moing.backend.domain.team.domain.entity.Team;
import com.moing.backend.domain.teamScore.domain.entity.TeamScore;

@Component
public class TeamScoreMapper {

	public static TeamScore mapToTeamScore(Team team) {
		return TeamScore.builder()
			.score(0L)
			.level(1L)
			.team(team)
			.build();
	}

}
