package com.lec.junit;

import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JunitTest1 {

	@Test
	void test() {
		log.info("test 실행");
		fail("Not yet implemented"); // 강제 fail
	}

	@Test
	void test2() {
		log.info("test2 실행");
		fail("Not yet implemented"); // 강제 fail
	}

	@Test
	void test3() {
		log.info("test3 실행");
		fail("Not yet implemented"); // 강제 fail
	}
	
}
