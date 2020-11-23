package com.lec.spring.scope.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.lec.spring.Score;

@Configuration
public class ScopeConfig {

	@Bean
	@Scope("singleton") // default
	public Score score1() {
		return new Score(100, 100, 100, "아주 좋아요!");
	}

	@Bean
	@Scope("prototype") // option
	public Score score2() {
		return new Score(10, 20, 30, "꼴등 이네요 ? !");
	}

}
