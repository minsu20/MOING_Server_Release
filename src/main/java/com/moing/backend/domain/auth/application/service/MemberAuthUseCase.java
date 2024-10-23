package com.moing.backend.domain.auth.application.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.moing.backend.domain.auth.exception.AccountAlreadyExistedException;
import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.member.domain.service.MemberSaveService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberAuthUseCase {

	private final MemberSaveService memberSaveService;

	@Transactional
	public Member auth(String fcmToken, Member member, String providerInfo) {
		member.updateFcmToken(fcmToken);
		Member signInMember = memberSaveService.saveOrUpdateMemberWithSignIn(member);
		checkRegistration(signInMember, providerInfo);
		return signInMember;
	}

	// 다른 플랫폼으로 가입했으면 에러 출력
	private void checkRegistration(Member signInMember, String providerInfo) {
		if (!providerInfo.contains((signInMember.getProvider().name().toLowerCase())))
			throw new AccountAlreadyExistedException();
	}

}
