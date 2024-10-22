package com.moing.backend.domain.auth.application.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignInRequest {
	@NotBlank(message = "socialToken 을 입력해주세요.")
	private String socialToken;

	@NotBlank(message = "fcmToken 을 입력해주세요.")
	private String fcmToken;

}

