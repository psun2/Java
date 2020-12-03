package com.lec.spring.autowired2;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AutowireMain02 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:autowiredCtx2.xml");
		System.out.println("컨테이너 생성\n");
		
		for(String name : ctx.getBeanDefinitionNames()) {
			System.out.println(ctx.getBean(name));
		}
		
		ctx.close();

	}

}
