package com.moing.backend.domain.history.domain.repository;

import com.moing.backend.domain.history.application.dto.response.GetAlarmHistoryResponse;
import com.moing.backend.domain.history.application.dto.response.QGetAlarmHistoryResponse;
import com.moing.backend.domain.team.domain.constant.ApprovalStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.moing.backend.domain.history.domain.entity.QAlarmHistory.alarmHistory;
import static com.moing.backend.domain.team.domain.entity.QTeam.team;

public class AlarmHistoryCustomRepositoryImpl implements AlarmHistoryCustomRepository {

    private final JPAQueryFactory queryFactory;

    public AlarmHistoryCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<GetAlarmHistoryResponse> findAlarmHistoriesByMemberId(Long memberId) {
        return queryFactory.select(new QGetAlarmHistoryResponse(alarmHistory.id,
                        alarmHistory.type,
                        alarmHistory.path,
                        alarmHistory.idInfo,
                        alarmHistory.title,
                        alarmHistory.body,
                        alarmHistory.name,
                        alarmHistory.isRead,
                        alarmHistory.createdDate))
                .from(alarmHistory)
                .where(alarmHistory.receiverId.eq(memberId))
                .orderBy(alarmHistory.createdDate.desc())
                .fetch();
    }
}
