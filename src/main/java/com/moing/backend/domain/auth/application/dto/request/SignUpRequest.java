package com.moing.backend.domain.auth.application.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.moing.backend.domain.member.domain.constant.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class SignUpRequest {

	@NotBlank(message = "nickName 을 입력해주세요.")
	@Size(min = 1, max = 10, message = "nickName 은 최소 1개, 최대 10개의 문자만 입력 가능합니다.")
	private final String nickName;

	private final Gender gender;

	private final String birthDate;
}
