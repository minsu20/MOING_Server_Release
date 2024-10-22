package com.moing.backend.domain.mypage.application.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.member.domain.service.MemberGetService;
import com.moing.backend.global.config.security.jwt.TokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SignOutUseCase {

	private final TokenUtil tokenUtil;
	private final MemberGetService memberGetService;

	public void signOut(String socialId) {
		tokenUtil.expireRefreshToken(socialId);
		Member member = memberGetService.getMemberBySocialId(socialId);
		member.signOut();
	}
}
