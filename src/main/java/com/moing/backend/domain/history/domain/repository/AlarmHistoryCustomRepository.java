package com.moing.backend.domain.history.domain.repository;

import java.util.List;

import com.moing.backend.domain.history.application.dto.response.GetAlarmHistoryResponse;

public interface AlarmHistoryCustomRepository {

	List<GetAlarmHistoryResponse> findAlarmHistoriesByMemberId(Long memberId);

	String findUnreadAlarmCount(Long memberId);
}
