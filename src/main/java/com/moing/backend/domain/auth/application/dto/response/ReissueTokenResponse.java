package com.moing.backend.domain.auth.application.dto.response;

import com.moing.backend.global.response.TokenInfoResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class ReissueTokenResponse {

	private final String accessToken;
	private final String refreshToken;

	public static ReissueTokenResponse from(TokenInfoResponse tokenInfoResponse) {
		return ReissueTokenResponse.builder()
			.accessToken(tokenInfoResponse.getAccessToken())
			.refreshToken(tokenInfoResponse.getRefreshToken())
			.build();
	}

}
