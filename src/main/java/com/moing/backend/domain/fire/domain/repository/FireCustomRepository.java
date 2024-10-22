package com.moing.backend.domain.fire.domain.repository;

import java.util.List;
import java.util.Optional;

import com.moing.backend.domain.fire.application.dto.res.FireReceiveRes;

public interface FireCustomRepository {

	boolean hasFireCreatedWithinOneHour(Long throwMemberId, Long receiveMemberId);

	Optional<List<FireReceiveRes>> getFireReceivers(Long teamId, Long missionId, Long memberId);

	Long getTodayFires();

	Long getYesterdayFires();
}
