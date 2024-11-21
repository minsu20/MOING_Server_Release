package com.moing.backend.global.config.security.jwt;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.moing.backend.domain.member.domain.entity.Member;
import com.moing.backend.domain.member.domain.service.MemberGetService;
import com.moing.backend.global.config.redis.RedisUtil;
import com.moing.backend.global.response.TokenInfoResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenManager implements InitializingBean {

	private static final String ADDITIONAL_INFO = "isAdditionalInfoProvided";
	private final RedisUtil redisUtil;
	private final MemberGetService memberQueryService;

	@Value("${jwt.privateKey}")
	private String privateKeyPem;

	@Value("${jwt.publicKey}")
	private String publicKeyPem;

	@Value("${jwt.access-token-period}")
	private long accessTokenValidityTime;

	@Value("${jwt.refresh-token-period}")
	private long refreshTokenValidityTime;

	private Key privateKey;
	private Key publicKey;

	@Override
	public void afterPropertiesSet() {
		try {
			this.privateKey = RSAKeyLoader.loadPrivateKey(privateKeyPem);
			this.publicKey = RSAKeyLoader.loadPublicKey(publicKeyPem);
		} catch (Exception e) {
			log.error("키 변환 중 오류 발생: {}", e.getMessage());
			throw new IllegalStateException("키 초기화 실패", e);
		}
	}

	public TokenInfoResponse createToken(Member member, boolean isAdditionalInfoProvided) {
		Claims claims = getClaims(member, isAdditionalInfoProvided);
		claims.put("jti", UUID.randomUUID().toString());

		Date now = new Date();
		Date accessTokenValidity = new Date(now.getTime() + this.accessTokenValidityTime);
		Date refreshTokenValidity = new Date(now.getTime() + this.refreshTokenValidityTime);

		String accessToken = Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(accessTokenValidity)
			.signWith(SignatureAlgorithm.RS256, privateKey)
			.compact();

		String refreshToken = Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(refreshTokenValidity)
			.signWith(SignatureAlgorithm.RS256, privateKey)
			.compact();

		return TokenInfoResponse.from("Bearer", accessToken, refreshToken, refreshTokenValidityTime);
	}

	public boolean verifyToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);

			String jti = claims.getBody().get("jti", String.class);
			if (redisUtil.isBlacklisted(jti)) {
				log.info("블랙리스트에 등록된 JWT입니다. jti: {}", jti);
				return false;
			}

			return claims.getBody().getExpiration().after(new Date());
		} catch (Exception e) {
			log.info("JWT 검증 실패: {}", e.getMessage());
			return false;
		}
	}

	public boolean verifyRefreshToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			log.info("Refresh Token 만료: {}", e.getMessage());
			return false;
		} catch (Exception e) {
			log.error("Refresh Token 검증 실패: {}", e.getMessage());
			return false;
		}
	}


	//refresh token 관련
	public void storeRefreshToken(String socialId, TokenInfoResponse token) {
		redisUtil.save(token.getRefreshToken(), socialId);
	}

	public TokenInfoResponse tokenReissue(String token) {

		String socialId = getSocialId(token);
		Member member = memberQueryService.getMemberBySocialId(socialId);
		String storedRefreshToken = redisUtil.findById(socialId).orElseThrow(NotFoundRefreshToken::new);

		if (storedRefreshToken == null || !storedRefreshToken.equals(token)) {
			throw new NotFoundRefreshToken();
		}

		// Token 생성
		TokenInfoResponse newToken = createToken(member, true);

		// Token 저장
		storeRefreshToken(socialId, newToken);

		return newToken;
	}

	//토큰 만료시키기
	public void expireRefreshToken(String socialId) {
		redisUtil.deleteById(socialId);
	}

	// get 함수
	public boolean getAdditionalInfoProvided(String token) {
		Claims claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
		return claims.get(ADDITIONAL_INFO, Boolean.class);
	}

	private static Claims getClaims(Member member, boolean isAdditionalInfoProvided) {
		// claim 에 socialId 정보 추가
		Claims claims = Jwts.claims().setSubject(member.getSocialId());
		claims.put(ADDITIONAL_INFO, isAdditionalInfoProvided);
		return claims;
	}

	private Date getExpiration(String token) {
		return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody().getExpiration();
	}

	public String getSocialId(String token) {
		return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody().getSubject();
	}

}

