package com.lec.spring.aop02;

import com.lec.spring.beans.Service;

public class MyService22 extends Service{

	@Override
	public void doAction() {
		// 공통기능 없다!
		printInfo();   // 핵심기능
		
	}

}
