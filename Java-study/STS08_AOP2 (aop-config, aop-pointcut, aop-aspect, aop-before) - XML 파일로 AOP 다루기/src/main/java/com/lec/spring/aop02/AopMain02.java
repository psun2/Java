package com.lec.spring.aop02;

import com.lec.spring.beans.Service;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopMain02 {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:aopCtx2.xml");
		System.out.println("ctx 생성");

		// TODO
		Service myService21 = ctx.getBean("service1", Service.class);
		Service myService22 = ctx.getBean("service2", Service.class);

		myService21.doAction();
		System.out.println();

		myService22.doAction();
		System.out.println();

		ctx.close();
		System.out.println("Main 종료");
	} // end main

} // end class
