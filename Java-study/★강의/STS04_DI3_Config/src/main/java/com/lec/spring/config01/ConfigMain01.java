package com.lec.spring.config01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ConfigMain01 {

	public static void main(String[] args) {
		AbstractApplicationContext ctxA, ctxB, ctxAB;
		
		ctxA = new GenericXmlApplicationContext("classpath:appCtx01_A.xml");
		System.out.println("ctxA 컨테이너 생성\n");

		ctxB = new GenericXmlApplicationContext("classpath:appCtx01_B.xml");
		System.out.println("ctxB 컨테이너 생성\n");

		System.out.println(ctxA.getBean("score1"));
		System.out.println(ctxB.getBean("score2"));

		
		ctxAB = new GenericXmlApplicationContext(
					//"classpath:appCtx01_A.xml", "classpath:appCtx01_B.xml");
				"classpath:appCtx01_B.xml", "classpath:appCtx01_A.xml");
		System.out.println("ctxAB 컨테이너 생성\n");
		
		System.out.println(ctxAB.getBean("score1"));
		System.out.println(ctxAB.getBean("score2"));
		
		
		System.out.println(ctxA.getBean("score1") == ctxAB.getBean("score1"));
			
		
		
		ctxA.close(); ctxB.close();
		ctxAB.close();
	}

}
















