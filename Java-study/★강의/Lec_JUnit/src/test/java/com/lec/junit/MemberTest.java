package com.lec.junit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

	@Test
	@DisplayName("회원생성")
	void create_new(){
		
		IllegalArgumentException ex = 
				assertThrows(IllegalArgumentException.class, ()-> new Member(-1));
		assertEquals("나이는 0보다 커야 합니다", ex.getMessage());
	}
	
	@Test
	@DisplayName("회원들 생성 시간")
	void create_new_time() {
		assertTimeout(Duration.ofMillis(100), ()->{
			for(int i = 0; i < 10; i++) {
				new Member(i);
				Thread.sleep(100);
			}
		});
	}
	
	@Test
	@DisplayName("AssertJ 사용")
	void test3() {
		Member child = new Member(20);
		assertThat(child.getAge())
			.isGreaterThan(0)
			.isLessThan(15)
			;
		
	}

}
















