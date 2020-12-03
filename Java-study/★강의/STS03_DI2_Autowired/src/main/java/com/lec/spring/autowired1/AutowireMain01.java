package com.lec.spring.autowired1;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AutowireMain01 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:autowiredCtx1.xml");
		System.out.println("컨테이너 생성\n");
		
		for(String name : ctx.getBeanDefinitionNames()) {
			System.out.println(ctx.getBean(name));
		}
		
		ctx.close();

	}

}
