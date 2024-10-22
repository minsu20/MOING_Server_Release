package com.moing.backend.domain.missionArchive.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.moing.backend.domain.missionArchive.domain.entity.MissionArchive;
import com.moing.backend.domain.missionArchive.domain.repository.MissionArchiveRepository;
import com.moing.backend.global.annotation.DomainService;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class MissionArchiveSaveService {

	private final MissionArchiveRepository missionArchiveRepository;

	public MissionArchive save(MissionArchive missionArchive) {
		return missionArchiveRepository.save(missionArchive);
	}

}
