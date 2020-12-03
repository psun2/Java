package com.lec.spring.environment;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public class EnvMain {

	public static void main(String[] args) {
		System.out.println("Main() 시작");
		
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		ConfigurableEnvironment env = ctx.getEnvironment(); // <-- ConfigurableApplicationContext  소속 메소드
		
		MutablePropertySources propertySources = env.getPropertySources();
		
		// PropertySources 에 PropertySource(PS) 추가		
		try {
			// PS 하나 생성해서 PropertySources 끝에 추가하기 : addLast() 
			propertySources.addLast(
					new ResourcePropertySource("classpath:admin.auth"));
			propertySources.addLast(
					new ResourcePropertySource("classpath:member.auth"));
			
			// 이제, Environment 에서 원하는 Property 에 접근 가능
			// 굳이 '어느 PS' 의  '어느 property' 를 요청할 필요가 없다
			// '어느 property' 에 대한것만 요청하면
			// propertySources 에 소속된 모든 PS 들을 다 스캔하면서 찾아낸다!
			System.out.println(env.getProperty("admin.id"));
			System.out.println(env.getProperty("admin.pw"));
			System.out.println(env.getProperty("member.id"));
			System.out.println(env.getProperty("member.pw"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GenericXmlApplicationContext gCtx =
				(GenericXmlApplicationContext)ctx;
		gCtx.load("classpath:appCtx1.xml");   // ctx.load() 
		gCtx.refresh();
		
		AdminConnection adminConnection = gCtx.getBean("adminConnection", AdminConnection.class);
		
		System.out.println("admin ID : " + adminConnection.getAdminId());
		System.out.println("admin pw : " + adminConnection.getAdminPw());
		
		
		gCtx.close();
		System.out.println("Main() 종료");
	}

}













