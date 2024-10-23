package com.moing.backend.domain.auth.application.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class KakaoUserResponse {
	private final Long id;
	private final Properties properties;
	private final KakaoAccount kakaoAccount;

	@Getter
	@AllArgsConstructor
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class Properties {
		private final String nickname;
	}

	@Getter
	@AllArgsConstructor
	public static class KakaoAccount {
		private final String email;
	}

}
