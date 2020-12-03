package com.lec.spring.lifecycle01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class LifeCycleMain01 {

	public static void main(String[] args) {
		System.out.println("Main() 시작");
		
		// 먼저 컨테이너 생성
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext();
		System.out.println("컨테이너 생성");
		
		// 나중에 설정을 load 할수도 있다.
		ctx.load("classpath:appCtx01_A.xml");
		System.out.println("설정 load 완료");
		
		ctx.refresh();   // <-- refresh() 해야 제대로 설정 완료 (빈 생성) 된다.
		System.out.println("컨테이너 refresh 완료");
		
		
		ctx.close();
		System.out.println("Main() 종료");
	}

}










