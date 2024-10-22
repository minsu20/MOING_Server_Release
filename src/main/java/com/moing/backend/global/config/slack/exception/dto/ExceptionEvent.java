package com.moing.backend.global.config.slack.exception.dto;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionEvent {

	private final HttpServletRequest request;
	private final Exception exception;
}
