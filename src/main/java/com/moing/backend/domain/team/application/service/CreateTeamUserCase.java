package com.moing.backend.domain.team.application.service;

import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.member.domain.service.MemberGetService;
import com.moing.backend.domain.team.application.dto.request.CreateTeamRequest;
import com.moing.backend.domain.team.application.dto.response.CreateTeamResponse;
import com.moing.backend.domain.team.domain.entity.Team;
import com.moing.backend.domain.team.domain.service.TeamSaveService;
import com.moing.backend.domain.teamMember.domain.service.TeamMemberGetService;
import com.moing.backend.domain.teamMember.domain.service.TeamMemberSaveService;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateTeamUserCase {

    private final MemberGetService memberGetService;
    private final TeamSaveService teamSaveService;
    private final TeamMemberSaveService teamMemberSaveService;

    public CreateTeamResponse createTeam(CreateTeamRequest createTeamRequest, String socialId){
        Member member = memberGetService.getMemberBySocialId(socialId);
        Team team=Team.createTeam(createTeamRequest, member);
        teamSaveService.saveTeam(team);
        teamMemberSaveService.saveTeamMember(team, member);
        return new CreateTeamResponse(team.getTeamId());
    }
}