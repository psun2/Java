package com.lec.spring.config04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.lec.spring.Score;

@Configuration
@ImportResource("classpath:appCtx04.xml") // XML 설정을 Jacva 설정 안으로 가져오기
public class AppConfig04 {

	@Bean
	public Score score2() { // id = "score2" 빈 생성
		return new Score(60, 20, 45, "나빠요");
	}

	
}
