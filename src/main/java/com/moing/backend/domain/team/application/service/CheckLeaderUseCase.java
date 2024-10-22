package com.moing.backend.domain.team.application.service;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.team.domain.entity.Team;
import com.moing.backend.domain.team.exception.NotAuthByTeamException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CheckLeaderUseCase {
	public boolean isTeamLeader(Member member, Team team) {
		return Objects.equals(member.getMemberId(), team.getLeaderId());
	}

	public void validateTeamLeader(Member member, Team team) {
		if (!isTeamLeader(member, team)) {
			throw new NotAuthByTeamException();
		}
	}

}
