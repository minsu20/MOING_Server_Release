package com.moing.backend.global.config.s3;

import org.springframework.beans.factory.annotation.Value;

import com.moing.backend.global.annotation.Util;

@Util
public class ImageUrlUtil {
	public static String prefix;

	@Value("${image.prefix}")
	public void setPrefix(String value) {
		prefix = value;
	}
}
