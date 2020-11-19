package com.lec.spring.autowired1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AutowiredMain01 {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		System.out.println("Main 시작\n");

		String configLocation = "classpath:autowiredCtx1.xml";
		AbstractApplicationContext context = new GenericXmlApplicationContext(configLocation);
		System.out.println("컨테이너 생성");

		System.out.println("생성된 빈의 갯수 : " + context.getBeanDefinitionCount() + " 개");
		
		for(String name : context.getBeanDefinitionNames()) {
			System.out.println(context.getBean(name));
		}

		context.close();

		System.out.println("\nMain 끝");
		long end = System.currentTimeMillis();
		System.out.println(end - start + "ms");

	}

}
