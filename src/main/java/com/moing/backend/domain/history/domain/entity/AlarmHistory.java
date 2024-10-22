package com.moing.backend.domain.history.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.moing.backend.global.entity.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmHistory extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "alarm_history_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AlarmType type;

	@Column(nullable = false)
	private String path;

	private String idInfo;

	private Long receiverId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String body;

	@Column(nullable = false)
	private String name;

	private boolean isRead;

	public void readAlarmHistory() {
		this.isRead = true;
	}
}
