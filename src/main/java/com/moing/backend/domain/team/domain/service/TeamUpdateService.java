package com.moing.backend.domain.team.domain.service;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;

import com.moing.backend.domain.team.domain.repository.TeamRepository;
import com.moing.backend.global.annotation.DomainService;

import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class TeamUpdateService {

	private final TeamRepository teamRepository;
	private final ApplicationEventPublisher eventPublisher;

	public void updateTeamStatus(boolean isApproved, List<Long> teamIds) {
		teamRepository.updateTeamStatus(isApproved, teamIds);
	}
}
