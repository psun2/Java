package com.lec.spring.di02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.lec.spring.MessageBean;

/*
Dependency Injection (DI, 의존주입)
필요한 객체는 누가 만들어 사용하나?

방법2 : 외부에서 만들어 주입 (injection)
	스프링 특징 의존주입 (Dependenty Injection) 사용
	즉 '외부' 에서 만들어 놓은 것을 가져옴.
*/

public class DIMain02 {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		System.out.println("Main 시작");

		// 스프링 설정파일 resource 명시
		String configLocation = "classpath:messageCtx.xml";

		// ApplicationContext 객체(스프링 컨테이너)를 생성
		AbstractApplicationContext context = new GenericXmlApplicationContext(configLocation);
		System.out.println("컨테이너 생성");

		MessageBean msg = context.getBean("messageBean", MessageBean.class);
		// 1번째 인자는 bean 의 id
		// 2번째 인자로는 형변환할 class 를 입력합니다.

		msg.sayHello();
		// MessageKor() 생성
		// 컨테이너 생성

		if (context != null)
			context.close();

		// 외부(컨테이너)에서 만들어진 객체 주입(injection)
		long end = System.currentTimeMillis();
		System.out.println("Main 종료");
		System.out.println(end - start + "ms");

	}

}
