package com.lec.spring.aop01;

import com.lec.spring.beans.Service;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopMain01 {

	public static void main(String[] args) {
		System.out.println("Main 시작");
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:aopCtx1.xml");
		System.out.println("ctx생성");

		// TODO
		Service service1 = ctx.getBean("service1", Service.class);
		Service service2 = ctx.getBean("service2", Service.class);

		// 핵심코드 호출
		service1.doAction();
		System.out.println();

		service2.doAction();
		System.out.println();

		ctx.close();
		System.out.println("Main 종료");
	} // end main()

} // end class
