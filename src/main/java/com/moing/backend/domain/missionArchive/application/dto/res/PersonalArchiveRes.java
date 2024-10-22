package com.moing.backend.domain.missionArchive.application.dto.res;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class PersonalArchiveRes {

	private Long archiveId;
	private String nickname;
	private String profileImg;

	private String archive;
	private String createdDate;
	private String way;

	private String heartStatus;
	private int hearts;

	private String status;
	private Long count;

	private Long makerId;

	@Nullable
	private String contents;
	private Long comments;

}
