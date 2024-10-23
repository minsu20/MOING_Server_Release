package com.moing.backend.domain.auth.application.service.apple;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;

public class AppleToken {

	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Request {
		private String code;
		private String clientId;
		private String clientSecret;
		private String grantType;

		public static Request of(String code, String clientId, String clientSecret, String grantType) {
			Request request = new Request();
			request.code = code;
			request.clientId = clientId;
			request.clientSecret = clientSecret;
			request.grantType = grantType;
			return request;
		}
	}

	@Getter
	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Response {
		private String accessToken;
		private String expiresIn;
		private String idToken;
		private String refreshToken;
		private String tokenType;
		private String error;
	}

	@Getter
	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class RevokeRequest {
		private String clientId;
		private String clientSecret;
		private String token;

		public static RevokeRequest of(String clientId, String clientSecret, String token) {
			RevokeRequest request = new RevokeRequest();
			request.clientId = clientId;
			request.clientSecret = clientSecret;
			request.token = token;
			return request;
		}
	}
}
