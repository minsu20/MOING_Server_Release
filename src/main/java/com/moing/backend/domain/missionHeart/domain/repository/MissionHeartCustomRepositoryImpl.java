package com.moing.backend.domain.missionHeart.domain.repository;

import static com.moing.backend.domain.missionHeart.domain.entity.QMissionHeart.*;

import javax.persistence.EntityManager;

import com.moing.backend.domain.missionHeart.domain.entity.MissionHeart;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MissionHeartCustomRepositoryImpl implements MissionHeartCustomRepository {

	private final JPAQueryFactory queryFactory;

	public MissionHeartCustomRepositoryImpl(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	public boolean findAlreadyHeart(Long memberId, Long archiveId) {

		return queryFactory
			.selectFrom(missionHeart)
			.where(
				missionHeart.pushMemberId.eq(memberId),
				missionHeart.missionArchive.id.eq(archiveId)
			).fetchCount() > 0;

	}

	public MissionHeart findByMemberIdAndArchiveId(Long memberId, Long archiveId) {

		return queryFactory
			.selectFrom(missionHeart)
			.where(
				missionHeart.pushMemberId.eq(memberId),
				missionHeart.missionArchive.id.eq(archiveId)
			).fetchFirst();

	}

}
