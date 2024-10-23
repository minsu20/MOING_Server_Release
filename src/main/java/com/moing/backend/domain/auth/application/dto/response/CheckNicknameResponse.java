package com.moing.backend.domain.auth.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
public class CheckNicknameResponse {
	private final Boolean isDuplicated;
}
