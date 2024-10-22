package com.moing.backend.domain.missionArchive.application.dto.res;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MissionArchivePhotoRes {
	Long teamId;
	List<String> photo;
}
