package com.moing.backend.domain.teamScore.domain.repository;

import com.moing.backend.domain.teamScore.domain.entity.TeamScore;

public interface TeamScoreCustomRepository {

	TeamScore findTeamScoreByTeam(Long teamId);

}
