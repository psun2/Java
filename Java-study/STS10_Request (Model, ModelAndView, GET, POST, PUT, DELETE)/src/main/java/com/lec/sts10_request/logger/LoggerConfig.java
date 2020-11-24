package com.lec.sts10_request.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.lec.sts10_request.controller.BoardController;

@Configuration
@EnableAspectJAutoProxy
public class LoggerConfig {

	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger(BoardController.class);
	}

	@Bean
	public LoggerAspect loggerAspect() {
		return new LoggerAspect();
	}

}
