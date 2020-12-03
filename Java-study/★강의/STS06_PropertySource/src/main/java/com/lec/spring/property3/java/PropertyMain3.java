package com.lec.spring.property3.java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PropertyMain3 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(DBConfig.class);
		
		DBConn conn = ctx.getBean("dbConfig", DBConn.class);
		
		System.out.println("id : " + conn.getId());
		System.out.println("pw : " + conn.getPw());
		System.out.println("url : " + conn.getUrl());
		System.out.println("port : " + conn.getPort());
		
		ctx.close();
	}

}
















