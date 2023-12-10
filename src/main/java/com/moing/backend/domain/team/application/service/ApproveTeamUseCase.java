package com.moing.backend.domain.team.application.service;

import com.moing.backend.domain.history.domain.entity.AlarmType;
import com.moing.backend.domain.team.application.dto.response.GetLeaderInfoResponse;
import com.moing.backend.domain.team.domain.service.TeamGetService;
import com.moing.backend.domain.team.domain.service.TeamUpdateService;
import com.moing.backend.global.config.fcm.dto.event.SingleFcmEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.moing.backend.domain.history.domain.entity.PagePath.HOME_PATH;
import static com.moing.backend.global.config.fcm.constant.ApproveTeamMessage.APPROVE_TEAM_MESSAGE;

@Service
@RequiredArgsConstructor
@Transactional
public class ApproveTeamUseCase {

    private final TeamUpdateService teamUpdateService;
    private final TeamGetService teamGetService;
    private final ApplicationEventPublisher eventPublisher;

    public void approveTeams(List<Long> teamIds){
        teamUpdateService.updateTeamStatus(true, teamIds);
        List<GetLeaderInfoResponse> leaderInfos=teamGetService.getLeaderInfoResponses(teamIds);
        for(GetLeaderInfoResponse info: leaderInfos){
            String title=APPROVE_TEAM_MESSAGE.title(info.getLeaderName(), info.getTeamName());
            String body= APPROVE_TEAM_MESSAGE.body();

//            eventPublisher.publishEvent(new SingleFcmEvent(info.getLeaderFcmToken(), title, body, info.getLeaderId(), "", info.getTeamName(), AlarmType.APPROVE_TEAM, HOME_PATH.getValue()));
        }
    }
}
