package com.lec.spring.lifecycle.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lec.spring.Score;

@Configuration
public class LifeCycleConfig {

	@Bean
	public Score score() {
		return new Score(70, 60, 50, "나빠요");
	}

}
