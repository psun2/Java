package com.lec.spring.property.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class PropertyMain {
	public static void main(String[] args) {
		System.out.println("Main() 시작\n");

		GenericXmlApplicationContext genericXmlApplicationContext = new GenericXmlApplicationContext();
		genericXmlApplicationContext.load("classpath:propertySetting.xml");
		genericXmlApplicationContext.refresh();

		DBConn dbInfo1 = genericXmlApplicationContext.getBean("dbInfo1", DBConn.class);
		DBConn dbInfo2 = genericXmlApplicationContext.getBean("dbInfo2", DBConn.class);

		System.out.println(dbInfo1 + " setter(property)로 생성");
		// DBConn(id=system, pw=123456, url=db.news.net, port=3309) setter(property)로 생성
		System.out.println(dbInfo2 + " 생성자(constructor-args)로 생성");
		// DBConn(id=system, pw=123456, url=db.news.net, port=3309)
		// 생성자(constructor-args)로 생성

		genericXmlApplicationContext.close();
		System.out.println("\nMain() 종료");
	}
}
