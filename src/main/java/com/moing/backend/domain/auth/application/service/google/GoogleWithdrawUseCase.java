package com.moing.backend.domain.auth.application.service.google;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.moing.backend.domain.auth.application.service.WithdrawProvider;
import com.moing.backend.domain.auth.application.service.google.utils.GoogleClient;

import lombok.RequiredArgsConstructor;

@Service("googleWithdraw")
@RequiredArgsConstructor
public class GoogleWithdrawUseCase implements WithdrawProvider {

	private final GoogleClient googleClient;

	public void withdraw(String token) throws IOException {
		googleClient.revoke(token);
	}

}
