package com.moing.backend.domain.team.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class UpdateTeamRequest {

	private String name;

	private String introduction;

	private String profileImgUrl;
}
