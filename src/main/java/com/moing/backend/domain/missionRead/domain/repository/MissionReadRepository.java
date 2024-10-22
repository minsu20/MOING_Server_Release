package com.moing.backend.domain.missionRead.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.mission.domain.entity.Mission;
import com.moing.backend.domain.missionRead.domain.entity.MissionRead;
import com.moing.backend.domain.team.domain.entity.Team;

public interface MissionReadRepository extends JpaRepository<MissionRead, Long> {

	List<MissionRead> findMissionReadByMissionAndMemberAndTeam(Mission mission, Member member, Team team);
}
