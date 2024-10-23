package com.moing.backend.global.config.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.moing.backend.domain.member.domain.service.MemberGetService;
import com.moing.backend.global.config.security.filter.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private final TokenManager tokenManager;

	private final MemberGetService memberQueryService;

	@Override
	public void configure(HttpSecurity http) {
		JwtAuthFilter customFilter = new JwtAuthFilter(tokenManager, memberQueryService);
		//UsernamePasswordAuthenticationFilter 앞에 필터로 JwtFilter 추가
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
