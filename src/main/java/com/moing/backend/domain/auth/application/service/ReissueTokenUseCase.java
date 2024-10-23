package com.moing.backend.domain.auth.application.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.moing.backend.domain.auth.application.dto.response.ReissueTokenResponse;
import com.moing.backend.global.config.security.jwt.NotFoundRefreshToken;
import com.moing.backend.global.config.security.jwt.TokenManager;
import com.moing.backend.global.response.TokenInfoResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReissueTokenUseCase {

	private final TokenManager tokenManager;

	@Transactional
	public ReissueTokenResponse reissueToken(String token) {
		// refresh 토큰이 유효한지 확인
		if (token != null && tokenManager.verifyRefreshToken(token)) {
			// 토큰 새로 받아오기
			TokenInfoResponse newToken = tokenManager.tokenReissue(token);

			return ReissueTokenResponse.from(newToken);
		}
		throw new NotFoundRefreshToken();
	}
}
