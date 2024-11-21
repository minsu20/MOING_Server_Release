package com.moing.backend.global.config.security.jwt;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAKeyLoader {

	public static PrivateKey loadPrivateKey(String privateKeyString) throws Exception {
		// 문자열에서 공백 및 줄바꿈 제거
		String cleanedKey = privateKeyString.replaceAll("\\s", "");

		// Base64 디코딩
		byte[] keyBytes = Base64.getDecoder().decode(cleanedKey);

		// PKCS8 키 스펙 생성
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

		// RSA KeyFactory를 통해 PrivateKey 생성
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(keySpec);
	}

	public static PublicKey loadPublicKey(String publicKeyString) throws Exception {
		// 문자열에서 공백 및 줄바꿈 제거
		String cleanedKey = publicKeyString.replaceAll("\\s", "");

		// Base64 디코딩
		byte[] keyBytes = Base64.getDecoder().decode(cleanedKey);

		// X509 키 스펙 생성
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

		// RSA KeyFactory를 통해 PublicKey 생성
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(keySpec);
	}
}
