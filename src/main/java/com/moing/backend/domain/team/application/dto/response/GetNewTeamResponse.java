package com.moing.backend.domain.team.application.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetNewTeamResponse {

	private String teamName;
	private String category;
	private String promise;
	private String introduction;
	private String profileImgUrl;
	private LocalDateTime createdDate;

}
