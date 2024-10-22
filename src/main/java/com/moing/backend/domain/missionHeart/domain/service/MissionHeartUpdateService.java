package com.moing.backend.domain.missionHeart.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.moing.backend.domain.missionHeart.domain.entity.MissionHeart;
import com.moing.backend.domain.missionHeart.domain.repository.MissionHeartRepository;
import com.moing.backend.global.annotation.DomainService;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class MissionHeartUpdateService {

	private final MissionHeartRepository missionHeartRepository;

	public MissionHeart update(MissionHeart missionHeart) {

		MissionHeart updateHeart = missionHeartRepository.findByMemberIdAndArchiveId(missionHeart.getPushMemberId(),
			missionHeart.getMissionArchive().getId());
		updateHeart.updateHeartStatus(missionHeart.getHeartStatus());
		//        updateHeart.changeByHeart(missionHeart.getMissionArchive());

		return missionHeartRepository.save(updateHeart);

	}

}
