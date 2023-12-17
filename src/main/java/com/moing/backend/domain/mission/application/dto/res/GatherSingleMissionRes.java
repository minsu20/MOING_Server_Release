package com.moing.backend.domain.mission.application.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GatherSingleMissionRes {
    private Long missionId;
    private Long teamId;
    private String teamName;
    private String missionTitle;
    private String dueTo;
    private String status;


}
