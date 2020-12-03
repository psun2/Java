package com.lec.junit;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppTest {

	private static App app;

	@BeforeAll
	static void app() {
		app = new App();
	}

	// 실습1
	@Test
	@DisplayName("1번문제")
	public void test1() {
		assertThat(app.add(1, 5)).isEqualTo(6);
		assertThat(app.add(5, 5)).isEqualTo(10);
		assertThat(app.add(7, 5)).isEqualTo(12);
		assertThat(app.add(1, 2)).isEqualTo(3);
		assertThat(app.add(8, 8)).isEqualTo(16);

		log.info("test1 통과");
	}

	// 실습2
	@Test
	@DisplayName("2번문제")
	public void test2() {
		assertThat(app.comp("Hello", "Hello")).isEqualTo(true);
		assertThat(app.comp("word", "word")).isEqualTo(true);
		assertThat(app.comp("park", "park")).isEqualTo(true);
		assertThat(app.comp("sung", "sung")).isEqualTo(true);
		assertThat(app.comp("un", "un")).isEqualTo(true);

		log.info("test2 통과");
	}

	// 실습3
	@Test
	@DisplayName("3번문제")
	public void test3() {
		assertThat(app.toNumber("10")).isEqualTo(10);
		assertThat(app.toNumber("9")).isEqualTo(9);
		assertThat(app.toNumber("8")).isEqualTo(8);
		assertThat(app.toNumber("7")).isEqualTo(7);
		assertThat(app.toNumber("6")).isEqualTo(6);
		assertThat(app.toNumber("5")).isEqualTo(5);

		log.info("test3 통과");
	}

	// 실습4
	@Test
	@DisplayName("4번문제")
	public void test4() {

		assertThat(app.toDigit("010-4537-2233")).isEqualTo("01045372233");
		assertThat(app.toDigit("2012년06월07일")).isEqualTo("20120607");
		
		String test = null;
		
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update("안녕하세요".getBytes(StandardCharsets.UTF_8));
			
			test = String.format("%0128x", new BigInteger(digest.digest()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertThat(app.toDigit(test)).isEqualTo("266464812688843215182641393619691548302446626878226916893040372290696622");

		log.info("test4 통과");
	}

	@AfterAll
	static void end() {
		log.info("모든 테스트 종료");
	}

}
