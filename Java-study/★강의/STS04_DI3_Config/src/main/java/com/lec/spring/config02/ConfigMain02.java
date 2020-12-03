package com.lec.spring.config02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigMain02 {

	public static void main(String[] args) {
		// 'Java 설정 파일' 사용한 컨텍스트 생성
		AnnotationConfigApplicationContext ctxA = 
				new AnnotationConfigApplicationContext(AppConfig02.class);
		
		System.out.println("ctxA 컨테이너 생성");

		
		for(String name : ctxA.getBeanDefinitionNames()) {
			System.out.println(ctxA.getBean(name));
		}
		
		
		ctxA.close();
	}

}





