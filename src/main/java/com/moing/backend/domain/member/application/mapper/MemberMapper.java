package com.moing.backend.domain.member.application.mapper;

import org.springframework.stereotype.Component;

import com.moing.backend.domain.auth.application.dto.response.GoogleUserResponse;
import com.moing.backend.domain.auth.application.dto.response.KakaoUserResponse;
import com.moing.backend.domain.member.domain.constant.RegistrationStatus;
import com.moing.backend.domain.member.domain.constant.Role;
import com.moing.backend.domain.member.domain.constant.SocialProvider;
import com.moing.backend.domain.member.domain.entity.Member;

@Component
public class MemberMapper {

	public static Member createKakaoMember(KakaoUserResponse kakaoUserResponse) {

		String email = kakaoUserResponse.getKakaoAccount().getEmail();

		if (email.length() > 50)
			email = email.substring(0, 50);

		return Member.builder()
			.socialId(SocialProvider.KAKAO + "@" + kakaoUserResponse.getId())
			.provider(SocialProvider.KAKAO)
			.email(email)
			.role(Role.USER)
			.registrationStatus(RegistrationStatus.UNCOMPLETED)
			.build();
	}

	public static Member createAppleMember(String socialId, String email) {

		if (email.length() > 50)
			email = email.substring(0, 50);

		return Member.builder()
			.socialId(SocialProvider.APPLE + "@" + socialId)
			.provider(SocialProvider.APPLE)
			.email(email)
			.role(Role.USER)
			.registrationStatus(RegistrationStatus.UNCOMPLETED)
			.build();
	}

	public static Member createGoogleMember(GoogleUserResponse googleUserResponse) {
		String email = googleUserResponse.getEmail();

		if (email.length() > 50)
			email = email.substring(0, 50);

		return Member.builder()
			.socialId(SocialProvider.GOOGLE + "@" + googleUserResponse.getSub())
			.provider(SocialProvider.GOOGLE)
			.email(email)
			.role(Role.USER)
			.registrationStatus(RegistrationStatus.UNCOMPLETED)
			.build();
	}
}
