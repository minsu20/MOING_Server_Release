package com.moing.backend.domain.history.application.service;

import com.moing.backend.domain.history.application.dto.response.GetAlarmHistoryResponse;
import com.moing.backend.domain.history.domain.service.AlarmHistoryGetService;
import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.member.domain.service.MemberGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAlarmHistoryUseCase {

    private final MemberGetService memberGetService;
    private final AlarmHistoryGetService alarmHistoryGetService;

    /**
     * 알림 히스토리 조회
     */
    public List<GetAlarmHistoryResponse> getAllAlarmHistories(String socialId) {
        Member member = memberGetService.getMemberBySocialId(socialId);
        return alarmHistoryGetService.getAlarmHistories(member.getMemberId());
    }
}
