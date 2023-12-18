package com.springboard.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

/**
 *  암호화 구조를 이용한 데이터 보호
 *  단방향 암호화 : 암호화는 가능하나 복호화 불가능	ex) 비밀번호 암호화
 *  양방향 암호화 : 암복호화가 가능한 방식		ex) e-book(DRM), 전자서명
 *  		대칭키 방식(e-book(DRM)) : 비밀키 하나로 암복호화 처리, 비밀키 공유라는 상황 필요.	
 *  		비대칭키 방식(전자서명) : 한쌍의 키를 생성하고, 암호화와 복호화를 서로 다른른 키로 처리함.
 *  				public key(공개키) / private key(개인키)
 *
 */
@Slf4j
class PasswordEncoderTest {

	private PasswordEncoder passwordEncoder;
	
	@BeforeEach
	void setUp() {
		passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Test
	void test2() {
		String saved = "{bcrypt}$2a$10$UC9KYTRQtda6ekmrF9N6C.XEDUi1AvLpXUrd8to5nEjNk9j99T8mC";
		String input = "java";
		log.info("인증 성공 여부 : {}", passwordEncoder.matches(input, saved));
	}
	
	@Test
	void test() {
		String plain = "java";
		passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encoded =passwordEncoder.encode(plain);
		log.info("plain : {}, encoded : {}",plain, encoded);
	}

}
