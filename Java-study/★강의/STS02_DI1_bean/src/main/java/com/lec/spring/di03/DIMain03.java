package com.lec.spring.di03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.lec.spring.Score;

public class DIMain03 {

	public static void main(String[] args) {
		System.out.println("Main 시작");

		AbstractApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:scoreCtx.xml");
		System.out.println("컨테이너 생성\n");
		
		Score score = null;
		
		score = ctx.getBean("myScore1", Score.class);
		System.out.println(score);
		
		score = (Score)ctx.getBean("myScore2");
		System.out.println(score);
		
		System.out.println(ctx.getBean("myScore3"));
		System.out.println(ctx.getBean("myScore4"));
		
		System.out.println("생성된 빈의 개수: " + ctx.getBeanDefinitionCount());
		for(String name : ctx.getBeanDefinitionNames()) {
			System.out.println(name + " " + ctx.getBean(name));
		}
		
		int i;
		byte b = 10;
		i = b;
		
		byte b1 = 10, b2 = 20;
		byte b3 = 10 + 20;
		//b3 = b1 + b2;  // int 30
		
		//i = 30;
		//b3 = i;
		
		// byte + byte => int
		// byte + short => int
		// int + byte => int
		// long + int => long		
		
		// byte -> short -> int -> long -> float -> douuble
		//                  ↑
		//             char ┛ 
		
		//new Score(b1, b2, b3, "되요");
		
		
		ctx.close();
		System.out.println("Main 종료");
	}

}













