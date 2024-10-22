package com.moing.backend.domain.member.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.member.domain.repository.MemberRepository;
import com.moing.backend.domain.member.exception.NotFoundBySocialIdException;
import com.moing.backend.domain.member.exception.NotFoundRemindAlarmException;
import com.moing.backend.global.annotation.DomainService;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class MemberGetService {
	private final MemberRepository memberRepository;

	public Member getMemberBySocialId(String socialId) {
		return memberRepository.findNotDeletedBySocialId(socialId).orElseThrow(() -> new NotFoundBySocialIdException());
	}

	public Member getMemberByMemberId(Long memberId) {
		return memberRepository.findNotDeletedByMemberId(memberId).orElseThrow(() -> new NotFoundBySocialIdException());
	}

	public Long getTodayNewMembers() {
		return memberRepository.getTodayNewMembers();
	}

	public Long getYesterdayNewMembers() {
		return memberRepository.getYesterdayNewMembers();
	}

	public List<Member> getAllMemberOfPushAlarm() {
		return memberRepository.findAllMemberOnPushAlarm().orElseThrow(NotFoundRemindAlarmException::new);
	}
}
