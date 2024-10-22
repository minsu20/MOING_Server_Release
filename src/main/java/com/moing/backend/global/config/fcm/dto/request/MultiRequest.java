package com.moing.backend.global.config.fcm.dto.request;

import java.util.List;

import com.moing.backend.domain.history.application.dto.response.MemberIdAndToken;
import com.moing.backend.domain.history.domain.entity.AlarmType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MultiRequest {

	private List<MemberIdAndToken> memberIdAndTokens;

	private String title;

	private String body;

	private String idInfo;

	private String name;

	private AlarmType alarmType;

	private String path;

}
