package com.moing.backend.domain.member.domain.service;

import javax.transaction.Transactional;

import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.member.domain.repository.MemberRepository;
import com.moing.backend.global.annotation.DomainService;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class MemberDeleteService {

	private final MemberRepository memberRepository;

	public void deleteMember(Member member) {
		this.memberRepository.delete(member);
	}
}
