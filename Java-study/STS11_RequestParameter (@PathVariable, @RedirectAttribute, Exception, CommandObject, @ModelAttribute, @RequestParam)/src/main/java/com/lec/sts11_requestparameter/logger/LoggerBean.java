package com.lec.sts11_requestparameter.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.lec.sts11_requestparameter.Controller;

@Configuration
@EnableAspectJAutoProxy
public class LoggerBean {

	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger(Controller.class);
	}
	
	@Bean
	public LoggerAspect loggerAspect() {
		return new LoggerAspect();
	}
}
