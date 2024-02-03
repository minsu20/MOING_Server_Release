package com.moing.backend.domain.teamScore.application.service;


import com.moing.backend.domain.mission.domain.entity.Mission;
import com.moing.backend.domain.mission.domain.entity.constant.MissionType;
import com.moing.backend.domain.mission.domain.service.MissionQueryService;
import com.moing.backend.domain.missionState.domain.service.MissionStateQueryService;
import com.moing.backend.domain.team.domain.entity.Team;
import com.moing.backend.domain.team.domain.service.TeamGetService;
import com.moing.backend.domain.team.domain.service.TeamSaveService;
import com.moing.backend.domain.teamScore.application.dto.TeamScoreRes;
import com.moing.backend.domain.teamScore.domain.entity.TeamScore;
import com.moing.backend.domain.teamScore.domain.service.TeamScoreQueryService;
import com.moing.backend.domain.teamScore.domain.service.TeamScoreSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeamScoreUpdateUseCase {

    private final MissionQueryService missionQueryService;
    private final TeamScoreQueryService teamScoreQueryService;

    private final float SCORE_BASE_NUM = 10;
    private final long BONUS_SCORE_ONCE_MISSION = 1L;
    private final long BONUS_SCORE_REPEAT_MISSION = 2L;

    /**
     * 매번 미션 인증 시 점수 적립
     */
    public void gainScoreByArchive(Mission mission) {


        Team team = mission.getTeam();
        TeamScore teamScore = teamScoreQueryService.findTeamScoreByTeam(team.getTeamId());

        Integer numOfMember = team.getNumOfMember();
        Long gainScore = calculateScoreByArchive(numOfMember);

        teamScore.updateScore(gainScore);

    }

    private Long calculateScoreByArchive(Integer numOfMember) {
        float allPeople = Float.valueOf(numOfMember);
        return Math.round(SCORE_BASE_NUM/allPeople) * 2L;
    }

    /*
     * 보너스 점수 적립
     * 한번 미션은 소모임원 마지막 인증 시 호출, 반복 미션은 각 멤버 당 마지막 인증시 호출
     */
    public void gainScoreByBonus(Mission mission) {

        Team team = mission.getTeam();
        TeamScore teamScore = teamScoreQueryService.findTeamScoreByTeam(team.getTeamId());

        Integer numOfMember = team.getNumOfMember();


        /**
         *  한번미션 일 경우 1 * 소모임원 수, 반복미션 일 경우 2점 update
         *  한번 미션은 소모임원 마지막 인증 시 호출, 반복 미션은 각 멤버 당 마지막 인증시 호출
         */

        if (mission.getType().equals(MissionType.ONCE)) {
            teamScore.updateScore(BONUS_SCORE_ONCE_MISSION * numOfMember);
        } else {
            teamScore.updateScore(BONUS_SCORE_REPEAT_MISSION);
        }

    }









}
