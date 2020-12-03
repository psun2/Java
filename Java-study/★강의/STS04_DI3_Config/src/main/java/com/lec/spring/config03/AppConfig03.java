package com.lec.spring.config03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lec.spring.Score;

@Configuration
public class AppConfig03 {
	
	@Bean
	public Score score1() {
		return new Score(60, 30, 45, "나빠요");
	}
	
}
