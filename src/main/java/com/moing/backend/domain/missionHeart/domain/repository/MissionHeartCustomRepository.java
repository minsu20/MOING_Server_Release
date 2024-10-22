package com.moing.backend.domain.missionHeart.domain.repository;

import org.springframework.stereotype.Repository;

import com.moing.backend.domain.missionHeart.domain.entity.MissionHeart;

@Repository
public interface MissionHeartCustomRepository {

	boolean findAlreadyHeart(Long memberId, Long archiveId);

	MissionHeart findByMemberIdAndArchiveId(Long memberId, Long archiveId);

}
