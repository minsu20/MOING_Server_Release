package com.moing.backend.global.config.fcm.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MultiResponse {
	private final String response;
	private final List<String> failedTokens;

}
