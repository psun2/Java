package com.lec.spring.di03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.lec.spring.Score;

public class DIMain03 {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		System.out.println("Main 시작");

		String configLocation = "classpath:scoreCtx.xml";

		// 이 시점에서 이미 Bean 는 Container에 생성됩니다.
		AbstractApplicationContext context = new GenericXmlApplicationContext(configLocation);
		System.out.println("컨테이너 생성\n");

		Score score = null;
		// Exception in thread "main"
		// org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean
		// named 'myScore' available
		// 잘못된 ID, 찾을수 없는 ID
		// score = context.getBean("myScore", Score.class);
		score = context.getBean("myScore1", Score.class);
		System.out.println(score);

		System.out.println("생성된 빈의 개수 : " + context.getBeanDefinitionCount() + " 개");

		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}

		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(context.getBean(name));
		}

		if (context != null)
			context.close();

		long end = System.currentTimeMillis();
		System.out.println("\nMain 종료");
		System.out.println(end - start + "ms");
		
		int i;
		byte b = 10;
		i = b;

		byte b1 = 10, b2 = 20;
		byte b3 = 10 + 20;
		// b3 = b1 + b2; // + 의 결과는 4byte 의 int 입니다.
		// b3 는 byte이므로 integer을 받을 수 없음

		i = 30;
		// b3 = i;

		// byte + byte => int
		// byte + short => int
		// byte + int => int
		// long + int => long

		// byte -> short -> int -> long -> float -> douuble
		// ↑
		// char ┛

	}

}
