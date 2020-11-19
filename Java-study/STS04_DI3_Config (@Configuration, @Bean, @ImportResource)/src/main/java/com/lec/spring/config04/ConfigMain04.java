package com.lec.spring.config04;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ConfigMain04 {

	public static void main(String[] args) {
		// XML 로 부터 설정
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig04.class);
		
		// XML 에서 설정
		System.out.println(ctx.getBean("score1"));

		// Java 에서 설정
		System.out.println(ctx.getBean("score2"));

		
		ctx.close();
	}

}
