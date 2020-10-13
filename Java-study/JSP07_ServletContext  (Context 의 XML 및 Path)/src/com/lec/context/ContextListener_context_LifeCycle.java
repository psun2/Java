package com.lec.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// 사용시 기본 적으로 web.xml 에 등록 해야합니다.
public class ContextListener_context_LifeCycle implements ServletContextListener {

	// 컨텍스트 생성시 호출
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized() => context 생성시 메모리상에 등록전 딱 한번 호출 됩니다.");
	}

	// 컨텍스트 소멸시 호출
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed() => context가 죽었을 시 호출됩니다.");
	}

}
