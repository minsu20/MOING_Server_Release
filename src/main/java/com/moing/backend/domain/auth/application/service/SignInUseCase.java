package com.moing.backend.domain.auth.application.service;

import static com.moing.backend.domain.member.domain.constant.RegistrationStatus.*;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.moing.backend.domain.auth.application.dto.request.SignInRequest;
import com.moing.backend.domain.auth.application.dto.response.SignInResponse;
import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.member.domain.service.MemberGetService;
import com.moing.backend.global.config.security.jwt.TokenManager;
import com.moing.backend.global.config.security.util.AuthenticationUtil;
import com.moing.backend.global.response.TokenInfoResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignInUseCase {

	private final MemberAuthUseCase internalAuthService;
	private final TokenManager tokenManager;
	private final Map<String, SignInProvider> signInProviders;
	private final MemberGetService memberGetService;

	@Transactional
	public SignInResponse signIn(SignInRequest signInRequest, String providerInfo) {
		//1. 사용자 정보 가져오기
		Member member = getUserDataFromPlatform(signInRequest.getSocialToken(), providerInfo);
		//2. 로그인 및 회원가입
		Member authenticatedMember = internalAuthService.auth(signInRequest.getFcmToken(), member, providerInfo);
		//3. security 처리
		AuthenticationUtil.makeAuthentication(authenticatedMember);
		//4. token 만들기
		TokenInfoResponse tokenResponse = tokenManager.createToken(authenticatedMember,
			authenticatedMember.getRegistrationStatus() == COMPLETED);
		//5. refresh token 저장
		tokenManager.storeRefreshToken(authenticatedMember.getSocialId(), tokenResponse);

		return SignInResponse.from(tokenResponse, authenticatedMember.getRegistrationStatus());
	}

	private Member getUserDataFromPlatform(String accessToken, String providerInfo) {
		SignInProvider signInProvider = signInProviders.get(providerInfo + "SignIn");
		if (signInProvider == null) {
			throw new IllegalArgumentException("Unknown provider: " + providerInfo);
		}
		return signInProvider.getUserData(accessToken);
	}

	@Transactional
	public SignInResponse testSignIn(String fcmToken, String socialId, String providerInfo) {
		//1. 사용자 정보 가져오기
		Member member = memberGetService.getMemberBySocialId(socialId);
		//2. 로그인
		Member authenticatedMember = internalAuthService.auth(fcmToken, member, providerInfo);
		//3. security 처리
		AuthenticationUtil.makeAuthentication(authenticatedMember);
		//4. token 만들기
		TokenInfoResponse tokenResponse = tokenManager.createToken(authenticatedMember,
			authenticatedMember.getRegistrationStatus().equals(COMPLETED));
		//5. refresh token 저장
		tokenManager.storeRefreshToken(authenticatedMember.getSocialId(), tokenResponse);

		return SignInResponse.from(tokenResponse, authenticatedMember.getRegistrationStatus());
	}
}
