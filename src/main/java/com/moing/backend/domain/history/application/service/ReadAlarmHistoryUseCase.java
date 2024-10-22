package com.moing.backend.domain.history.application.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.moing.backend.domain.history.domain.entity.AlarmHistory;
import com.moing.backend.domain.history.domain.service.AlarmHistoryGetService;
import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.member.domain.service.MemberGetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReadAlarmHistoryUseCase {

	private final MemberGetService memberGetService;
	private final AlarmHistoryGetService alarmHistoryGetService;

	/**
	 * 알림 히스토리 읽기
	 */
	@Transactional
	public void readAlarmHistory(String socialId, Long alarmHistoryId) {
		Member member = memberGetService.getMemberBySocialId(socialId);
		AlarmHistory alarmHistory = alarmHistoryGetService.getAlarmHistory(alarmHistoryId, member.getMemberId());
		alarmHistory.readAlarmHistory();
	}
}
