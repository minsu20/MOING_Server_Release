package com.moing.backend.domain.auth.application.service.apple;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keys {

	private List<PubKey> keys;

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class PubKey {
		private String alg;

		private String e;

		private String kid;

		private String kty;

		private String n;

		private String use;
	}
}

