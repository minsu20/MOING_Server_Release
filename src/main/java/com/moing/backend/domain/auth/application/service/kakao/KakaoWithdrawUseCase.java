package com.moing.backend.domain.auth.application.service.kakao;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.moing.backend.domain.auth.application.service.WithdrawProvider;
import com.moing.backend.domain.auth.application.service.kakao.utils.KakaoClient;

import lombok.RequiredArgsConstructor;

@Service("kakaoWithdraw")
@RequiredArgsConstructor
public class KakaoWithdrawUseCase implements WithdrawProvider {

	private final KakaoClient kakaoClient;

	public void withdraw(String token) throws IOException {

		kakaoClient.unlinkUser("Bearer " + token);
	}
}
