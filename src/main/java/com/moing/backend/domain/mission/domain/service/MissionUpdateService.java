package com.moing.backend.domain.mission.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.moing.backend.domain.mission.domain.entity.Mission;
import com.moing.backend.domain.mission.domain.repository.MissionRepository;
import com.moing.backend.global.annotation.DomainService;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class MissionUpdateService {

	private final MissionRepository missionRepository;

	public void updateMission(Mission mission) {
		missionRepository.save(mission);
	}
}
