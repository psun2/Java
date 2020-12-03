package com.lec.spring.profile4.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ProfileMain4 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
		// 활성화할 profile 선택
		ctx.getEnvironment().setActiveProfiles("run");
		
		
		ctx.load("appCtx4_dev.xml", "appCtx4_run.xml");
		ctx.refresh();

		ServerInfo info = ctx.getBean("serverInfo", ServerInfo.class);
		System.out.println("ip : " + info.getIpNum());
		System.out.println("port : " + info.getPortNum());
		ctx.close();
	}

}
