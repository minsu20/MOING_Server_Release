package com.moing.backend.domain.missionRead.domain.repository;

import static com.moing.backend.domain.mission.domain.entity.QMission.*;
import static com.moing.backend.domain.missionRead.domain.entity.QMissionRead.*;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;

public class MissionReadRepositoryUtils {
	public static BooleanExpression isMissionReadByMemberIdAndTeamId(Long memberId, Long teamId) {
		return JPAExpressions
			.select(missionRead.missionReadId)
			.from(missionRead)
			.where(missionRead.member.memberId.eq(memberId),
				missionRead.mission.team.teamId.eq(teamId),
				missionRead.mission.id.eq(mission.id))
			.exists();
	}

	public static BooleanExpression isMissionReadByMemberIdAndTeamIds(Long memberId, List<Long> teamIds) {
		return JPAExpressions
			.select(missionRead.missionReadId)
			.from(missionRead)
			.where(missionRead.member.memberId.eq(memberId),
				missionRead.mission.team.teamId.in(teamIds),
				missionRead.mission.id.eq(mission.id))
			.exists();
	}
}
