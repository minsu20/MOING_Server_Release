package com.moing.backend.global.config.fcm.dto.event;

import com.moing.backend.domain.history.domain.entity.AlarmType;
import com.moing.backend.domain.member.domain.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SingleFcmEvent {

	private Member member;
	private String title;
	private String body;
	private String idInfo;
	private String name;
	private AlarmType alarmType;
	private String path;
	private boolean isAlarmPush;

}
