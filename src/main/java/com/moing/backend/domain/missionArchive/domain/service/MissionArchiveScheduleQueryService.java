package com.moing.backend.domain.missionArchive.domain.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.missionArchive.domain.repository.MissionArchiveRepository;
import com.moing.backend.global.annotation.DomainService;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class MissionArchiveScheduleQueryService {

	private final MissionArchiveRepository missionArchiveRepository;

	public List<Member> getRemainMissionPeople() {
		return missionArchiveRepository.findHavingRemainMissionsByQuerydsl().orElseThrow();

	}

}
