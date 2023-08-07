package com.moing.backend.domain.member.domain.repository;

import com.moing.backend.domain.member.domain.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.moing.backend.domain.member.domain.entity.QMember.member;

public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory queryFactory;

    public MemberCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Member> findBySocialIdNotDeleted(String socialId) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .where(member.socialId.eq(socialId))
                .where(member.isDeleted.eq(false))
                .fetchFirst());
    }
}
