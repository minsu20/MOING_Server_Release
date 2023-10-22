package com.moing.backend.domain.mission.application.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GatherRepeatMissionRes {

    private Long missionId;
    private String teamName;
    private String missionTitle;
    private String totalNum;
    private String doneNum;

}
