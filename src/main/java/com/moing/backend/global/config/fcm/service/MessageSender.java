package com.moing.backend.global.config.fcm.service;

public interface MessageSender<T> {
	void send(T request);
}
