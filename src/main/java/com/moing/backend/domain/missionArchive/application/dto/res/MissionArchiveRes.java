package com.moing.backend.domain.missionArchive.application.dto.res;

import lombok.*;

import javax.annotation.Nullable;
import java.util.List;
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class MissionArchiveRes {

    private Long archiveId;
    private String archive;
    private String createdDate;
    private String status;
    private Long count;
    private String heartStatus;
    private Long hearts;

    public void updateHeartStatus(boolean status) {
        if (status) {
            this.heartStatus = "True";
        }else{
            this.heartStatus = "False";
        }
    }


}
