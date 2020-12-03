package com.lec.spring.scope;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.lec.spring.Score;

public class ScopeMain {

	public static void main(String[] args) {
		System.out.println("Main() 시작");
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx02.xml");
		System.out.println("-- 컨테이너 생성 완료 --");
		
		// scope="singleton"
		Score score11 = ctx.getBean("score1", Score.class);
		System.out.println(score11);
		
		Score score12 = ctx.getBean("score1", Score.class);
		score12.setComment("절망편");
		
		System.out.println(score11);
		System.out.println(score12);
		
		System.out.println(score11 == score12);  // true
		
		
		// scope="prototype"
		// getBean() 할때마다 bean 인스턴스 새로이 생성
		Score score21 = ctx.getBean("score2", Score.class);
		Score score22 = ctx.getBean("score2", Score.class);
		score21.setComment("21입니다");
		score22.setComment("22입니다");
		System.out.println(score21);
		System.out.println(score22);
		System.out.println(score21 == score22);
		
		ctx.close();
		System.out.println("Main() 종료");
	}

}















