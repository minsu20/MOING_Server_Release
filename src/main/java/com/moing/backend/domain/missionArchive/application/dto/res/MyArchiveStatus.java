package com.moing.backend.domain.missionArchive.application.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class MyArchiveStatus {
	private boolean end;
	private String status;
}
