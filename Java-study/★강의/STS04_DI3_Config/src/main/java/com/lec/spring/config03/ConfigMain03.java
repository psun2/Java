package com.lec.spring.config03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ConfigMain03 {

	public static void main(String[] args) {
		// XML로부터 설정
		AbstractApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:appCtx03.xml");
		
		// Java 에서 설정
		System.out.println(ctx.getBean("score1"));
		
		// XML 에서 설정
		System.out.println(ctx.getBean("score2"));
		
		ctx.close();

	}

	
	
	
	
}














