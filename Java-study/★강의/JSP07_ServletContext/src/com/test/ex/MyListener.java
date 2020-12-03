package com.test.ex;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {

	// 컨텍스트 소멸시 호출
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("JSP07 어플리케이션 종료");
	}

	// 컨텍스트 생성후 호출
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("JSP07 어플리케이션 시작");
	}

	
	
}
