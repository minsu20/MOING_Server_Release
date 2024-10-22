package com.moing.backend.domain.fire.domain.service;

import org.springframework.transaction.annotation.Transactional;

import com.moing.backend.domain.fire.domain.entity.Fire;
import com.moing.backend.domain.fire.domain.repository.FireRepository;
import com.moing.backend.global.annotation.DomainService;

import lombok.RequiredArgsConstructor;

@DomainService
@Transactional
@RequiredArgsConstructor
public class FireSaveService {

	private final FireRepository fireRepository;

	public Fire save(Fire fire) {
		return fireRepository.save(fire);
	}
}
