package com.lec.spring.profile5.java;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileMain5 {
	public static void main(String[] args) {
		System.out.println("개발환경 입력(dev / run) :");
		Scanner sc = new Scanner(System.in);
		String config = sc.next();
		sc.close();
		
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext();
		ctx.getEnvironment().setActiveProfiles(config);
		ctx.register(ApplicationConfigDev.class, ApplicationConfigRun.class);
		ctx.refresh();
		
		ServerInfo info = ctx.getBean("serverInfo", ServerInfo.class);
		System.out.println("ip : " + info.getIpNum());
		System.out.println("port : " + info.getPortNum());
		ctx.close();
		
	}
}













