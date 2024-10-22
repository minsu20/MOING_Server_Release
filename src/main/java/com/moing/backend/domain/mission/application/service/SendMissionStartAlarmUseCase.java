package com.moing.backend.domain.mission.application.service;

import static com.moing.backend.global.config.fcm.constant.NewMissionTitle.*;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import net.minidev.json.JSONObject;

import com.moing.backend.domain.history.application.dto.response.MemberIdAndToken;
import com.moing.backend.domain.history.application.dto.response.NewUploadInfo;
import com.moing.backend.domain.history.application.mapper.AlarmHistoryMapper;
import com.moing.backend.domain.history.domain.entity.AlarmType;
import com.moing.backend.domain.history.domain.entity.PagePath;
import com.moing.backend.domain.mission.domain.entity.Mission;
import com.moing.backend.domain.mission.domain.entity.constant.MissionStatus;
import com.moing.backend.domain.mission.domain.entity.constant.MissionType;
import com.moing.backend.domain.team.domain.entity.Team;
import com.moing.backend.domain.teamMember.domain.service.TeamMemberGetService;
import com.moing.backend.global.config.fcm.dto.event.MultiFcmEvent;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SendMissionStartAlarmUseCase {

	private final TeamMemberGetService teamMemberGetService;
	private final ApplicationEventPublisher eventPublisher;

	public void sendRepeatMissionStartAlarm(Mission mission) {
		Team team = mission.getTeam();
		String title = team.getName() + " " + NEW_REPEAT_MISSION_COMING.getTitle();
		String message = mission.getTitle();
		String type = mission.getType().toString();
		String status = mission.getStatus().toString();

		Optional<List<NewUploadInfo>> newUploadInfos = teamMemberGetService.getNewUploadInfo(team.getTeamId(), 0L);

		Optional<List<MemberIdAndToken>> memberIdAndTokensByPush = AlarmHistoryMapper.getNewUploadPushInfo(
			newUploadInfos);
		Optional<List<MemberIdAndToken>> memberIdAndTokensBySave = AlarmHistoryMapper.getNewUploadSaveInfo(
			newUploadInfos);
		// 알림 보내기
		eventPublisher.publishEvent(new MultiFcmEvent(title, message, memberIdAndTokensByPush, memberIdAndTokensBySave,
			createIdInfo(team.getTeamId(), mission.getId(), mission.getType(), mission.getStatus()), team.getName(),
			AlarmType.NEW_UPLOAD, PagePath.MISSION_PATH.getValue()));
	}

	private String createIdInfo(Long teamId, Long missionId, MissionType type, MissionStatus status) {
		JSONObject jo = new JSONObject();
		jo.put("isRepeated", type.equals(MissionType.REPEAT));
		jo.put("teamId", teamId);
		jo.put("missionId", missionId);
		jo.put("status", status.name());
		return jo.toJSONString();
	}
}

