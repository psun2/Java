package com.lec.sts12_validation.controller.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

import com.lec.sts12_validation.Controller;

@Configuration
@EnableAspectJAutoProxy
public class LoggerConfig {

	@Bean
	@Scope("singleton")
	public Logger logger() {
		return LoggerFactory.getLogger(Controller.class);
	}

	@Bean
	public LoggerAspect loggerAspect() {
		return new LoggerAspect();
	}

}
