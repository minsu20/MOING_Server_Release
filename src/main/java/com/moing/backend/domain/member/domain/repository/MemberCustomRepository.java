package com.moing.backend.domain.member.domain.repository;

import java.util.List;
import java.util.Optional;

import com.moing.backend.domain.member.domain.entity.Member;

public interface MemberCustomRepository {

	boolean checkNickname(String nickname);

	Optional<Member> findNotDeletedBySocialId(String socialId);

	Optional<Member> findNotDeletedByEmail(String email);

	Optional<Member> findNotDeletedByMemberId(Long id);

	Long getTodayNewMembers();

	Long getYesterdayNewMembers();

	Optional<List<Member>> findAllMemberOnPushAlarm();
}
