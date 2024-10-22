package com.moing.backend.domain.statistics.application.service;

import org.springframework.stereotype.Service;

import com.moing.backend.domain.statistics.domain.constant.DAUStatusType;
import com.moing.backend.domain.team.domain.service.TeamGetService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DAUTeam implements DAUProvider {

	private final TeamGetService teamGetService;

	@Override
	public Long getTodayStats() {
		return teamGetService.getTodayNewTeams();
	}

	@Override
	public Long getYesterdayStats() {
		return teamGetService.getYesterdayNewTeams();
	}

	@Override
	public DAUStatusType getSupportedType() {
		return DAUStatusType.DAILY_TEAM_COUNT;
	}
}
