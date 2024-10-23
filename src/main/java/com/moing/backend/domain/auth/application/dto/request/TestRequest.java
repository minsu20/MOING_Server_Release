package com.moing.backend.domain.auth.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TestRequest {
	private final String socialId;

	private final String fcmToken;
}
